package ru.t1murcoder.service;

import ru.t1murcoder.controller.dto.AttendanceDto;
import ru.t1murcoder.domain.Attendance;
import ru.t1murcoder.domain.Lesson;

import java.util.List;

public interface AttendanceService {
    AttendanceDto add(AttendanceDto attendanceDto);

    List<AttendanceDto> getAll();

    AttendanceDto getById(long id);

    List<AttendanceDto> getByStudentId(long id);

    List<AttendanceDto> getByLessonId(long id);

    AttendanceDto update(long id, AttendanceDto attendanceDto);

    void deleteById(long id);
}
