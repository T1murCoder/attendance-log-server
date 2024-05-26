package ru.t1murcoder.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.GregorianCalendar;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDto {
    private Long id;
    private Boolean isVisited;
    private Float points;
    private Long studentId;
    private Long lessonId;
    private GregorianCalendar lessonTimeStart;
}
