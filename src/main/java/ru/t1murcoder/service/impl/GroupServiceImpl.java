package ru.t1murcoder.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1murcoder.controller.dto.GroupDto;
import ru.t1murcoder.controller.dto.GroupWithoutStudentsDto;
import ru.t1murcoder.controller.dto.StudentDto;
import ru.t1murcoder.domain.*;
import ru.t1murcoder.exception.GroupNotFoundException;
import ru.t1murcoder.exception.UserNotFoundException;
import ru.t1murcoder.mapper.GroupMapper;
import ru.t1murcoder.repository.*;
import ru.t1murcoder.service.GroupService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final AttendanceRepository attendanceRepository;

    @Override
    @Transactional
    public GroupDto add(GroupDto groupDto, String teacherUsername) {
        Optional<Teacher> teacher = teacherRepository.findByUsername(teacherUsername);
        if (teacher.isEmpty())
            throw new UserNotFoundException("User with username " + teacherUsername + " not found");

        if (groupDto.getName() == null)
            throw new RuntimeException("Group must have name");

//        if (groupRepository.findByName(groupDto.getName()).isPresent()) // Временно отключено, нужно переделать
//            throw new RuntimeException("Group already exists");

        Group group = groupRepository.save(GroupMapper.toGroupEntity(groupDto));

        group.setTeacher(teacher.get());

        //TODO: Сделать проверку, что Student не занят
        List<Student> studentList = new ArrayList<>();

        for (Student s : group.getStudentList()) {
            Student student = studentRepository.findById(s.getId()).orElseThrow(
                    () -> new UserNotFoundException("Student not found")
            );

            student.setGroup(group);

            studentList.add(student);

        }

        group.setStudentList(studentList);

        return GroupMapper.toGroupDto(groupRepository.save(group));
    }

    @Override
    public List<GroupDto> getAll() {

        return groupRepository.findAll()
                .stream()
                .map(GroupMapper::toGroupDto)
                .collect(Collectors.toList());
    }

    @Override
    public GroupDto getById(long id) {
        Optional<Group> group = groupRepository.findById(id);

        if (group.isEmpty())
            throw new GroupNotFoundException("Group with ID " + id + " not found");

        return GroupMapper.toGroupDto(group.get());
    }

    @Override
    public List<GroupDto> getByTeacherUsername(String username) {
        Optional<Teacher> teacher = teacherRepository.findByUsername(username);

        if (teacher.isEmpty())
            throw new UserNotFoundException("User with username " + username + " not found");

        List<Group> groupList = groupRepository.findByTeacher(teacher.get());

        return groupList
                .stream()
                .map(GroupMapper::toGroupDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GroupWithoutStudentsDto> getByTeacherUsernameWithoutStudents(String username) {
        Optional<Teacher> teacher = teacherRepository.findByUsername(username);

        if (teacher.isEmpty())
            throw new UserNotFoundException("User with username " + username + " not found");

        List<Group> groupList = groupRepository.findByTeacher(teacher.get());

        return groupList
                .stream()
                .map(GroupMapper::toGroupWithoutStudentsDto)
                .collect(Collectors.toList());
    }

    @Override
    public GroupDto getByStudentId(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException("Student with ID " + id + " not found")
                );

        Group group = groupRepository.findByStudentListContains(student)
                .orElseThrow(
                        () -> new GroupNotFoundException("Group not found")
                );

        return GroupMapper.toGroupDto(group);
    }

    @Override
    public GroupDto update(long id, GroupDto groupDto) {

        Group group = groupRepository.findById(id).orElseThrow(
                () -> new GroupNotFoundException("Group with ID " + id + " not found")
        );

        List<Student> studentList = new ArrayList<>();

        for (Student s : GroupMapper.toGroupEntity(groupDto).getStudentList()) {
            Student student = studentRepository.findById(s.getId()).orElseThrow(
                    () -> new UserNotFoundException("Student not found")
            );

            student.setGroup(group);

            studentList.add(student);

        }

        group.setStudentList(studentList);

        return GroupMapper.toGroupDto(groupRepository.save(group));
    }


    @Override
    @Transactional
    public void removeStudentFromGroupById(Long groupId, Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(
                        () -> new UserNotFoundException("Student with ID " + studentId + " not found")
                );

        Group group = groupRepository.findById(groupId)
                .orElseThrow(
                        () -> new GroupNotFoundException("Group with ID " + groupId + " not found")
                );

        student.setGroup(null);

        for (Attendance attendance : student.getAttendanceList()) {
            attendanceRepository.delete(attendance);
        }

        studentRepository.save(student);
    }

    @Override
    @Transactional
    public GroupDto addStudentToGroupById(Long groupId, List<StudentDto> studentDtoList) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(
                        () -> new GroupNotFoundException("Group with ID " + groupId + " not found")
                );

        List<Lesson> lessonList = group.getLessonList();

        for (StudentDto studentDto : studentDtoList) {
            Student student = studentRepository.findById(studentDto.getId())
                    .orElseThrow(
                            () -> new UserNotFoundException("Student with ID " + studentDto.getId() + " not found")
                    );
            student.setGroup(group);
            studentRepository.save(student);

            for (Lesson lesson : lessonList) {
                Attendance attendance = Attendance.builder()
                        .lesson(lesson)
                        .student(student)
                        .isVisited(false)
                        .points(0)
                        .build();
                attendanceRepository.save(attendance);
            }

        }

        Group updated = groupRepository.findById(groupId)
                .orElseThrow(
                        () -> new GroupNotFoundException("Group with ID " + groupId + " not found")
                );;

        return GroupMapper.toGroupDto(updated);
    }

    @Override
    @Transactional
    public void deleteById(long id, String username) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group with ID " + id + " not found"));

        Teacher teacher = teacherRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Teacher with ID " + id + " not found"));

        if (!group.getTeacher().getUsername().equals(teacher.getUsername()))
            throw new RuntimeException("Only the owner can delete the group");

        groupRepository.deleteStudentRelationByGroupId(id);
        groupRepository.deleteAttendancesByGroupId(id);
        groupRepository.deleteQRCodesByGroupId(id);
        groupRepository.deleteLessonsByGroupId(id);
        groupRepository.deleteById(id);
    }


}
