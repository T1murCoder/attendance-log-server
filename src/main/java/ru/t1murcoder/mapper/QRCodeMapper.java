package ru.t1murcoder.mapper;

import lombok.experimental.UtilityClass;
import ru.t1murcoder.controller.dto.QRCodeDto;
import ru.t1murcoder.domain.QRCode;

@UtilityClass
public class QRCodeMapper {

    public QRCodeDto toQRCodeDto(QRCode qrCode) {

        QRCodeDto qrCodeDto = QRCodeDto.builder()
                .id(qrCode.getId())
                .createdAt(qrCode.getCreatedAt())
                .expiresAt(qrCode.getExpiresAt())
                .build();

        if (qrCode.getLesson() != null) {
            qrCodeDto.setLessonId(qrCode.getLesson().getId());
        }

        return qrCodeDto;
    }

    public QRCode toQRCodeEntity(QRCodeDto qrCodeDto) {

        QRCode qrCode = QRCode.builder()
                .createdAt(qrCodeDto.getCreatedAt())
                .expiresAt(qrCodeDto.getExpiresAt())
                .build();

        if (qrCodeDto.getId() != null) {
            qrCode.setId(qrCodeDto.getId());
        }

        return qrCode;
    }
}
