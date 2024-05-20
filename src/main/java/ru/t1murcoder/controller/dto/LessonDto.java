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
public class LessonDto {
    private Long id;

    private String theme;

    private GregorianCalendar timeStart;

    private GregorianCalendar timeEnd;

    private GregorianCalendar date;

    private Long groupId;
}
