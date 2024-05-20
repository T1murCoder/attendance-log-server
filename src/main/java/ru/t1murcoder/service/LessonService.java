package ru.t1murcoder.service;

import ru.t1murcoder.controller.dto.LessonDto;
import ru.t1murcoder.domain.Lesson;

import java.util.List;

public interface LessonService {
    LessonDto add(LessonDto lessonDto);

    List<LessonDto> getAll();

    LessonDto getById(long id);

    LessonDto update(LessonDto lessonDto);

    void deleteById(long id);
}
