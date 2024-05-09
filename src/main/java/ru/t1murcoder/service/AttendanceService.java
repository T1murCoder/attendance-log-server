package ru.t1murcoder.service;

import ru.t1murcoder.domain.Attendance;
import ru.t1murcoder.domain.Lesson;

import java.util.List;

public interface AttendanceService {
    Attendance add(Attendance attendance);

    List<Attendance> getAll();

    Attendance getById(long id);

    Attendance update(Attendance attendance);

    void deleteById(long id);
}
