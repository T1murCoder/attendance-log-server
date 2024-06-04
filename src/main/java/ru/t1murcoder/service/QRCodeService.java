package ru.t1murcoder.service;

import ru.t1murcoder.controller.dto.QRCodeDto;

import java.util.List;

public interface QRCodeService {
    QRCodeDto add(QRCodeDto qrCodeDto);

    List<QRCodeDto> getAll();

    QRCodeDto getById(Long id);

    void deleteById(Long id);
}
