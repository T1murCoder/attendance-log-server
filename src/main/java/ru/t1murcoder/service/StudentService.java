package ru.t1murcoder.service;

import ru.t1murcoder.domain.Group;
import ru.t1murcoder.domain.Student;

import java.util.List;

public interface StudentService {
    Student add(Student student);

    List<Student> getAll();

    Student getById(long id);

    Student update(Student student);
    Student getByUsername(String username);
    List<Student> getByGroup(Group group);

    void deleteById(long id);

    Student addAttendance(long studentId, long lessonId);
}
