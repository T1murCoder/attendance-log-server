//package ru.t1murcoder.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import ru.t1murcoder.domain.Student;
//
//import java.util.List;
//
//@RestController
//public class StudentController {
//    private final StudentServiceOld service;
//
//    @Autowired
//    public StudentController(StudentServiceOld service) {
//        this.service = service;
//    }
//
//    @PostMapping("/student")
//    public Student add(@RequestBody Student student) {
//        return service.add(student);
//    }
//
//    @GetMapping("/student")
//    public List<Student> getAll() {
//        return service.getAll();
//    }
//
//    @GetMapping("/student/{id}")
//    public Student getById(@PathVariable long id) {
//        return service.getById(id);
//    }
//
//    @PutMapping("/student")
//    public Student update(@RequestBody Student student) {
//        return service.update(student);
//    }
//
//    @DeleteMapping("/student/{id}")
//    public void deleteById(@PathVariable long id) {
//        service.deleteById(id);
//    }
//
//    @PutMapping("/student/{student_id}/lesson/{lesson_id}")
//    public Student addAttendance(@PathVariable long student_id, @PathVariable long lesson_id) {
//        return service.addAttendance(student_id, lesson_id);
//    }
//}
