package ru.t1murcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.t1murcoder.domain.Student;
import ru.t1murcoder.repository.StudentRepository;
import ru.t1murcoder.service.StudentService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student add(Student student) {
        return repository.save(student);
    }

    @Override
    public List<Student> getAll() {
        return repository.findAll();
    }

    @Override
    public Student getById(long id) {
        // Тут лучше сделать проверку на пустоту объекта методом .orElseThrow()
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Student update(Student student) {
        // TODO: Сделать чтобы нельзя было апдейдить не существующего ученика
        return repository.save(student);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
