package ru.t1murcoder.service;

import ru.t1murcoder.domain.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher add(Teacher teacher);

    List<Teacher> getAll();

    Teacher getById(long id);

    Teacher getByLogin(String login);

    Teacher update(long id, Teacher teacher);

    void deleteById(long id);
}
