package ru.t1murcoder.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentWithAttendancesDto {
    private Long id;
    private String name;
    private String surname;
    private Float points;
    private List<AttendanceDto> attendanceDtoList;
}
