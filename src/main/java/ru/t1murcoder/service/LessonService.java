package ru.t1murcoder.service;

import ru.t1murcoder.domain.Lesson;

import java.util.List;

public interface LessonService {
    Lesson add(Lesson lesson);

    List<Lesson> getAll();

    Lesson getById(long id);

    Lesson update(Lesson lesson);

    void deleteById(long id);
}
