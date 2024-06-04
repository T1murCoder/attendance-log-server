package ru.t1murcoder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1murcoder.controller.dto.QRCodeDto;
import ru.t1murcoder.domain.Lesson;
import ru.t1murcoder.domain.QRCode;
import ru.t1murcoder.exception.LessonNotFoundException;
import ru.t1murcoder.exception.QRCodeNotFoundException;
import ru.t1murcoder.mapper.QRCodeMapper;
import ru.t1murcoder.repository.LessonRepository;
import ru.t1murcoder.repository.QRCodeRepository;
import ru.t1murcoder.service.QRCodeService;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QRCodeServiceImpl implements QRCodeService {

    private final LessonRepository lessonRepository;
    private final QRCodeRepository qrCodeRepository;

    @Override
    public QRCodeDto add(QRCodeDto qrCodeDto) {
        Lesson lesson = lessonRepository.findById(qrCodeDto.getLessonId())
                .orElseThrow(
                        () -> new LessonNotFoundException("Lesson wtih ID " + qrCodeDto.getLessonId() + " not found")
                );

        GregorianCalendar timeCreate = new GregorianCalendar();
        GregorianCalendar timeExpires = new GregorianCalendar();

        timeExpires.add(Calendar.SECOND, QRCode.qrCodeLifeTime);

        QRCode qrCode = QRCodeMapper.toQRCodeEntity(qrCodeDto);
        qrCode.setCreatedAt(timeCreate);
        qrCode.setExpiresAt(timeExpires);
        qrCode.setLesson(lesson);

        return QRCodeMapper.toQRCodeDto(qrCodeRepository.save(qrCode));
    }

    @Override
    public List<QRCodeDto> getAll() {
        return qrCodeRepository.findAll()
                .stream()
                .map(QRCodeMapper::toQRCodeDto)
                .collect(Collectors.toList());
    }

    @Override
    public QRCodeDto getById(Long id) {
        QRCode qrCode = qrCodeRepository.findById(id)
                .orElseThrow(
                        () -> new QRCodeNotFoundException("QRCode with ID " + id + " not found")
                );

        return QRCodeMapper.toQRCodeDto(qrCode);
    }

    @Override
    public void deleteById(Long id) {
        QRCode qrCode = qrCodeRepository.findById(id)
                .orElseThrow(
                        () -> new QRCodeNotFoundException("QRCode with ID " + id + " not found")
                );

        qrCodeRepository.deleteById(id);
    }
}
