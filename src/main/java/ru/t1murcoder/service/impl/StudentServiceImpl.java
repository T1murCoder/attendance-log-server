package ru.t1murcoder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.t1murcoder.controller.dto.StudentDto;
import ru.t1murcoder.controller.dto.StudentWithAttendancesDto;
import ru.t1murcoder.controller.dto.UserProfileDto;
import ru.t1murcoder.controller.dto.UserRegisterDto;
import ru.t1murcoder.domain.*;
import ru.t1murcoder.exception.GroupNotFoundException;
import ru.t1murcoder.exception.UserNotFoundException;
import ru.t1murcoder.mapper.StudentMapper;
import ru.t1murcoder.mapper.UserMapper;
import ru.t1murcoder.repository.*;
import ru.t1murcoder.service.StudentService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
//    private final TeacherRepository teacherRepository;
    private final AuthorityRepository authorityRepository;
    private final AttendanceRepository attendanceRepository;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserProfileDto add(UserRegisterDto userRegisterDto) {

        if (userRepository.findByUsername(userRegisterDto.getUsername()).isPresent())
            throw new RuntimeException("Login is already occupied");

        Optional<Authority> optionalAuthority = authorityRepository.findByAuthority("ROLE_STUDENT");
        if (optionalAuthority.isEmpty()) throw new RuntimeException("Authority not found");

        Student student = UserMapper.toStudentEntity(userRegisterDto);
        student.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        Set<Authority> authoritySet = new HashSet<>();
        authoritySet.add(optionalAuthority.get());
        student.setAuthorities(authoritySet);

        Student save = studentRepository.save(student);

        return UserMapper.toUserProfileDto(save);
    }

    @Override
    public List<UserProfileDto> getAll() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream()
                .map(UserMapper::toUserProfileDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserProfileDto getById(long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty())
            throw new UserNotFoundException("Student not found");

        return UserMapper.toUserProfileDto(student.get());
    }

    @Override
    public List<StudentDto> getVacantStudents() {
        List<Student> studentList = studentRepository.findByGroupIsNull();
        return studentList.stream()
                .map(StudentMapper::toStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserProfileDto getByUsername(String username) {
        Optional<Student> student = studentRepository.findByUsername(username);

        if (student.isEmpty())
            throw new UserNotFoundException("Student not found");

        return UserMapper.toUserProfileDto(student.get());
    }

    @Override
    public List<StudentWithAttendancesDto> getByGroupId(long id) {
        Group group = groupRepository.findById(id).orElseThrow(
                () -> new GroupNotFoundException("Group with ID " + id + " not found")
        );

        return group.getStudentList().stream()
                .map(StudentMapper::toStudentWithAttendancesDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getByGroupIdTemp(long id) {
        Group group = groupRepository.findById(id).orElseThrow(
                () -> new GroupNotFoundException("Group with ID " + id + " not found")
        );

        return group.getStudentList().stream()
                .map(StudentMapper::toStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getAttendedStudentByLessonId(long id) {

        List<Attendance> attendanceList = attendanceRepository.findByLessonId(id);

        List<StudentDto> studentDtoList = new ArrayList<>();

        for (Attendance attendance : attendanceList) {
            if (attendance.getIsVisited()) {
                studentDtoList.add(StudentMapper.toStudentDto(attendance.getStudent()));
            }
        }

        return studentDtoList;
    }


    @Override
    public UserProfileDto checkUsernameIsPresent(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty())
            throw new UserNotFoundException("User with name " + username + " not found");

        return UserMapper.toUserProfileDto(userOptional.get());
    }

    @Override
    public UserProfileDto update(long id, UserProfileDto userProfileDto) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isEmpty())
            throw new UserNotFoundException("Student with ID " + id + " not found");

        Student student = studentOptional.get();
        if (userProfileDto.getName() != null) student.setName(userProfileDto.getName());
        if (userProfileDto.getSurname() != null) student.setSurname(userProfileDto.getSurname());
        if (userProfileDto.getTelegramUrl() != null) student.setTelegramUrl(userProfileDto.getTelegramUrl());
        if (userProfileDto.getGithubUrl() != null) student.setGithubUrl(userProfileDto.getGithubUrl());
        if (userProfileDto.getPhotoUrl() != null) student.setPhotoUrl(userProfileDto.getPhotoUrl());

        return UserMapper.toUserProfileDto(studentRepository.save(student));
    }

    @Override
    public void deleteById(long id) {
        // TODO: пофиксить удаление, сделать удаление присутствий
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Student with ID " + id + " not found"));

        studentRepository.deleteById(id);
    }

}
