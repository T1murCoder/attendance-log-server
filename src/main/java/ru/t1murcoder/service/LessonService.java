package ru.t1murcoder.service;

import ru.t1murcoder.controller.dto.LessonDto;

import java.util.List;

public interface LessonService {
    LessonDto add(LessonDto lessonDto);

    List<LessonDto> getAll();

    LessonDto getById(long id);

    List<LessonDto> getByTeacherUsername(String username);

    LessonDto markAttendedByLessonId(Long lessonId, Long studentId);

    LessonDto markAttendedByQRCodeId(Long qrCodeId, Long studentId);


    void deleteById(long id);
}
