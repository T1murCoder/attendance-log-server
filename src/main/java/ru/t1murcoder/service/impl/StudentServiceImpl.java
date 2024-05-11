package ru.t1murcoder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1murcoder.controller.dto.UserProfileDto;
import ru.t1murcoder.domain.Group;
import ru.t1murcoder.domain.Student;
import ru.t1murcoder.domain.User;
import ru.t1murcoder.exception.UserNotFoundException;
import ru.t1murcoder.mapper.UserMapper;
import ru.t1murcoder.repository.GroupRepository;
import ru.t1murcoder.repository.StudentRepository;
import ru.t1murcoder.repository.TeacherRepository;
import ru.t1murcoder.repository.UserRepository;
import ru.t1murcoder.service.StudentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    @Override
    public Student add(Student student) {

        if (studentRepository.findByUsername(student.getUsername()).isPresent()) {
            throw new RuntimeException("Student is already exists");
        }
        if (teacherRepository.findByUsername(student.getUsername()).isPresent())
            throw new RuntimeException("Login is already occupied");

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student getById(long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isEmpty())
            throw new RuntimeException("Student with ID " + id + " not found");


        return studentOptional.get();
    }

    @Override
    public Student update(Student student) {
        return null;
    }

    @Override
    public Student getByUsername(String username) {
        return null;
    }

    @Override
    public List<Student> getByGroup(Group group) {
        Optional<Group> group1 = groupRepository.findById(group.getId());

        if (group1.isEmpty())
            throw new RuntimeException("Group not found");

        List<Student> studentList = studentRepository.findByGroup(group1.get());

        return studentList;
    }

    @Override
    public void deleteById(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public UserProfileDto checkUsernameIsPresent(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty())
            throw new UserNotFoundException("User with name " + username + " not found");

        return UserMapper.toUserProfileDto(userOptional.get());
    }

}
