package ru.t1murcoder.service;

import ru.t1murcoder.controller.dto.GroupDto;
import ru.t1murcoder.domain.Group;
import ru.t1murcoder.domain.Lesson;
import ru.t1murcoder.domain.Student;
import ru.t1murcoder.domain.Teacher;

import java.util.List;

public interface GroupService {
    Group add(Group group);

    List<Group> getAll();

    Group getById(long id);

    Group getByName(String name);

    List<Group> getByTeacher(Teacher teacher);

    Group addStudent(long id, Student student);
    Group addLesson(long id, Lesson lesson);

    Group update(long id, Group group);

    void deleteById(long id);
}
