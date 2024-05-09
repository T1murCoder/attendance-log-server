//package ru.t1murcoder.repository;
//
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import ru.t1murcoder.domain.Group;
//import ru.t1murcoder.domain.Lesson;
//import ru.t1murcoder.domain.Student;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@DataJpaTest
//class StudentRepositoryTest {
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @Autowired
//    private LessonRepository lessonRepository;
//
//    @DisplayName("Должен искать учеников по Уроку")
//    @Test
//    void ShouldFindByLesson() {
//
//        Lesson lesson = Lesson.builder()
//                .id(1)
//                .build();
//
//        //lessonRepository.save(lesson);
//
//        Set<Lesson> lessonSet = new HashSet<>();
//        lessonSet.add(lesson);
//
//        Group group = Group.builder()
//                .id(1)
//                .build();
//
//        Student expectedStudent = Student.builder()
//                .id(1)
//                .name("Timur")
//                .password("123")
//                .lessonsAttendSet(lessonSet)
//                .build();
//
//        studentRepository.save(expectedStudent);
//        //studentRepository.addLessonToLessonsAttendSetByStudent(expectedStudent, lesson);
//
//        //FIXME: почему-то из Student могу получить список уроков, а из уроков не могу получить список студентов
//        //FIXME: + у меня не создает запись в таблице если передавать в студента сет уроков
//
//        List<Student> actualStudentList = studentRepository.findBylessonsAttendSetContaining(lesson);
//        Set<Lesson> actualLesson = lessonRepository.findByAttendedStudentSetContaining(expectedStudent);
//        System.out.println(actualStudentList.get(0).getLessonsAttendSet());
//        System.out.println(actualLesson.stream().findFirst().get().getAttendedStudentSet());
//
//        assertThat(actualStudentList.get(0).getLessonsAttendSet()).isEqualTo(expectedStudent.getLessonsAttendSet());
//    }
//}