package ru.t1murcoder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.t1murcoder.controller.dto.LessonDto;
import ru.t1murcoder.service.LessonService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson/")
public class LessonController {
    private final LessonService lessonService;

    @PostMapping()
    public LessonDto add(@RequestBody LessonDto lessonDto) {
        return lessonService.add(lessonDto);
    }

    @GetMapping()
    public List<LessonDto> getAll() {
        return lessonService.getAll();
    }

    @GetMapping("/{id}")
    public LessonDto getById(@PathVariable Long id) {
        return lessonService.getById(id);
    }

    @GetMapping("/teacher/")
    public List<LessonDto> getAllByTeacherUsername(Authentication authentication) {
        return lessonService.getByTeacherUsername(authentication.getName());
    }

    @PutMapping("/attendance/{lesson_id}/{student_id}")
    public LessonDto markAttendedByLessonId(@PathVariable(name = "lesson_id") Long lessonId, @PathVariable(name = "student_id") Long studentId) {
        return lessonService.markAttendedByLessonId(lessonId, studentId);
    }

    @PutMapping("/attendance/qrcode/{qr_code_id}/{student_id}")
    public LessonDto markAttendedByQRCodeId(@PathVariable(name = "qr_code_id") Long qrCodeId, @PathVariable(name = "student_id") Long studentId) {
        return lessonService.markAttendedByQRCodeId(qrCodeId, studentId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        lessonService.deleteById(id);
    }


}
