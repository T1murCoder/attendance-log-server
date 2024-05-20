package ru.t1murcoder.controller;

import lombok.RequiredArgsConstructor;
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

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        lessonService.deleteById(id);
    }


}
