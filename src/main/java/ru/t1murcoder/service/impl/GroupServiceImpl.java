package ru.t1murcoder.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1murcoder.controller.dto.GroupDto;
import ru.t1murcoder.controller.dto.GroupWithoutStudentsDto;
import ru.t1murcoder.domain.Group;
import ru.t1murcoder.domain.Student;
import ru.t1murcoder.domain.Teacher;
import ru.t1murcoder.exception.UserNotFoundException;
import ru.t1murcoder.mapper.GroupMapper;
import ru.t1murcoder.repository.GroupRepository;
import ru.t1murcoder.repository.StudentRepository;
import ru.t1murcoder.repository.TeacherRepository;
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

    @Override
    public GroupDto add(GroupDto groupDto, String teacherUsername) {
        //FIXME Сделать добавление групп вместе с созданием
        Optional<Teacher> teacher = teacherRepository.findByUsername(teacherUsername);

        if (teacher.isEmpty())
            throw new UserNotFoundException("User with username " + teacherUsername + " not found");

        if (groupDto.getName() == null)
            throw new RuntimeException("Group must have name");

        if (groupRepository.findByName(groupDto.getName()).isPresent())
            throw new RuntimeException("Group already exists");

        Group group = groupRepository.save(GroupMapper.toGroupEntity(groupDto));

        group.setTeacher(teacher.get());

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
            throw new RuntimeException("Group not found");

        return GroupMapper.toGroupDto(group.get());
    }

    @Override
    public GroupDto getByName(String name) {
        // TODO: Сделать поиск по имени группы
        return null;
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
    public GroupDto getByStudentUsername(String username) {
        // TODO: Сделать поиск группы по имени студента
        return null;
    }

    @Override
    public void deleteById(long id) {
        //FIXME Сделать чтобы удалялась группа, но не удалялись Student
        // TODO: тут сделать проверку, что группу удалить может только владелец
        groupRepository.deleteById(id);
    }


//    @Override
//    public Group add(Group group) {
//        Teacher teacher = group.getTeacher();
//        if (teacherRepository.findById(teacher.getId()).isEmpty())
//            throw new RuntimeException("Teacher not found");
//
//        if (group.getName() == null)
//            throw new RuntimeException("Group must have name");
//
//        if (groupRepository.findByName(group.getName()).isPresent())
//            throw new RuntimeException("Group already exists");
//
//        return groupRepository.save(group);
//    }
//
//    @Override
//    public List<Group> getAll() {
//        return groupRepository.findAll();
//    }
//
//    @Override
//    public Group getById(long id) {
//        Optional<Group> group = groupRepository.findById(id);
//
//        if (group.isEmpty())
//            throw new RuntimeException("Group not found");
//
//        return group.get();
//    }
//
//    @Override
//    public Group getByName(String name) {
//        Optional<Group> group = groupRepository.findByName(name);
//
//        if (group.isEmpty())
//            throw new RuntimeException("Group not found");
//
//        return group.get();
//    }
//
//    @Override
//    public List<Group> getByTeacher(Teacher teacher) {
//        List<Group> group = groupRepository.findByTeacher(teacher);
//
//        if (group.isEmpty())
//            throw new RuntimeException("Group not found");
//
//        return group;
//    }
//
//    @Override
//    public Group update(long id, Group group) {
////        Optional<Group> groupOptional = groupRepository.findById(id);
////
////        if (groupOptional.isEmpty()) throw new RuntimeException("Group with ID " + id + " not found");
////
////        Group newGroup = groupOptional.get();
////        newGroup.
//
//        return null;
//    }
//
//    @Override
//    public void deleteById(long id) {
//        groupRepository.deleteById(id);
//    }
}
