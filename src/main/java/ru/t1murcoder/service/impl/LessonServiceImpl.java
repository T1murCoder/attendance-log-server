package ru.t1murcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.t1murcoder.domain.Lesson;
import ru.t1murcoder.domain.Student;
import ru.t1murcoder.repository.LessonRepository;
import ru.t1murcoder.repository.StudentRepository;
import ru.t1murcoder.service.LessonService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, StudentRepository studentRepository) {
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Lesson add(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson getById(long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow();
        Set<Student> students = new HashSet<>(studentRepository.findBylessonsAttendSetContaining(lesson));
        lesson.setAttendedStudentSet(students);

        return lesson;
    }

    @Override
    public Lesson update(Lesson lesson) {
        // TODO: Сделать чтобы нельзя было апдейдить не существующий урок
        return lessonRepository.save(lesson);
    }

    @Override
    public void deleteById(long id) {
        lessonRepository.deleteById(id);
    }

}