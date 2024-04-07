package ru.t1murcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.t1murcoder.domain.Lesson;
import ru.t1murcoder.domain.Student;
import ru.t1murcoder.repository.LessonRepository;
import ru.t1murcoder.repository.StudentRepository;
import ru.t1murcoder.service.StudentService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, LessonRepository lessonRepository) {
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Student add(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student getById(long id) {
        // Тут лучше сделать проверку на пустоту объекта методом .orElseThrow()
        Student student = studentRepository.findById(id).orElseThrow();
        Set<Lesson> lessons = lessonRepository.findByAttendedStudentSetContaining(student);
        student.setLessonsAttendSet(lessons);
        return student;
    }

    @Override
    public Student update(Student student) {
        // TODO: Сделать чтобы нельзя было апдейдить не существующего ученика
        return studentRepository.save(student);
    }

    @Override
    public void deleteById(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student addAttendance(long student_id, long lesson_id) {
        Student student = studentRepository.findById(student_id).orElseThrow();
        Lesson lesson = lessonRepository.findById(lesson_id).orElseThrow();
        Set<Lesson> lessonSet = lessonRepository.findByAttendedStudentSetContaining(student);
        lessonSet.add(lesson);
        student.setLessonsAttendSet(lessonSet);
        return studentRepository.save(student);
    }
    // TODO: Сделать DTO, чтобы исключить возможность рекурсивного вызова toString(), equals(), hashCode()

    //    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such student")
//    private class NoSuchStudentFound extends Exception {
//    }
}
