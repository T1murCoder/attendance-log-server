package ru.t1murcoder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1murcoder.domain.Group;
import ru.t1murcoder.domain.Teacher;
import ru.t1murcoder.repository.GroupRepository;
import ru.t1murcoder.repository.TeacherRepository;
import ru.t1murcoder.service.TeacherService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public Teacher add(Teacher teacher) {

        if (teacherRepository.findByLogin(teacher.getLogin()).isPresent())
            throw new RuntimeException("Teacher is already exists");

        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getById(long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if (teacher.isEmpty())
            throw new RuntimeException("Teacher not found");

        return teacher.get();
    }

    @Override
    public Teacher getByLogin(String login) {
        Optional<Teacher> teacher = teacherRepository.findByLogin(login);

        if (teacher.isEmpty())
            throw new RuntimeException("Teacher not found");

        return teacher.get();
    }

    @Override
    public Teacher update(long id, Teacher teacher) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);

        if (teacherOptional.isEmpty())
            throw new RuntimeException("Teacher with ID " + id + " not found");

        Teacher teacher1 = teacherOptional.get();
        if (teacher.getName() != null) teacher1.setName(teacher.getName());
        if (teacher.getSurname() != null) teacher1.setSurname(teacher.getSurname());
        if (teacher.getPassword() != null) teacher1.setPassword(teacher.getPassword());
        if (teacher.getTelegramUrl() != null) teacher1.setTelegramUrl(teacher.getTelegramUrl());
        if (teacher.getGithubUrl() != null) teacher1.setGithubUrl(teacher.getGithubUrl());
        if (teacher.getPhotoUrl() != null) teacher1.setPhotoUrl(teacher.getPhotoUrl());

        return teacherRepository.save(teacher1);
    }

    @Override
    public void deleteById(long id) {
        teacherRepository.deleteById(id);
    }
}
