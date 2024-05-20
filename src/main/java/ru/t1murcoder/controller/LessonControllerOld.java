//package ru.t1murcoder.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import ru.t1murcoder.domain.Lesson;
//
//import java.util.List;
//
//@RestController
//public class LessonController {
//    private final LessonOldService service;
//
//    @Autowired
//    public LessonController(LessonOldService service) {
//        this.service = service;
//    }
//
//    @PostMapping("/lesson")
//    public Lesson add(@RequestBody Lesson Lesson) {
//        return service.add(Lesson);
//    }
//
//    @GetMapping("/lesson")
//    public List<Lesson> getAll() {
//        return service.getAll();
//    }
//
//    @GetMapping("/lesson/{id}")
//    public Lesson getById(@PathVariable long id) {
//        return service.getById(id);
//    }
//
//    @PutMapping("/lesson")
//    public Lesson update(@RequestBody Lesson Lesson) {
//        return service.update(Lesson);
//    }
//
//    @DeleteMapping("/lesson/{id}")
//    public void deleteById(@PathVariable long id) {
//        service.deleteById(id);
//    }
//
//}