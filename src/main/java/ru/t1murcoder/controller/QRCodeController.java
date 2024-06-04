package ru.t1murcoder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.t1murcoder.controller.dto.QRCodeDto;
import ru.t1murcoder.service.QRCodeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/qrcode/")
public class QRCodeController {

    private final QRCodeService qrCodeService;

    @PostMapping()
    public QRCodeDto add(@RequestBody QRCodeDto qrCodeDto) {
        return qrCodeService.add(qrCodeDto);
    }

    @GetMapping()
    public List<QRCodeDto> getAll() {
        return qrCodeService.getAll();
    }
}
