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
public class QRCodeDto {
    private Long id;

    private GregorianCalendar createdAt;

    private GregorianCalendar expiresAt;

    private Long lessonId;
}
