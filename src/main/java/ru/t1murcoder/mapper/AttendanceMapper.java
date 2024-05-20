package ru.t1murcoder.mapper;

import lombok.experimental.UtilityClass;
import ru.t1murcoder.controller.dto.AttendanceDto;
import ru.t1murcoder.domain.Attendance;

@UtilityClass
public class AttendanceMapper {

    public AttendanceDto toAttendanceDto(Attendance attendance) {

        AttendanceDto attendanceDto = AttendanceDto.builder()
                .id(attendance.getId())
                .isVisited(attendance.getIsVisited())
                .points(attendance.getPoints())
                .studentId(attendance.getStudent().getId())
                .lessonId(attendance.getLesson().getId())
                .build();

        return attendanceDto;
    }

    public Attendance toAttendanceEntity(AttendanceDto attendanceDto) {

        Attendance attendance = Attendance.builder()
                .isVisited(attendanceDto.getIsVisited())
                .points(attendanceDto.getPoints())
                .build();

        if (attendanceDto.getId() != null) attendance.setId(attendanceDto.getId());

        return attendance;
    }

}
