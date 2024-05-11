//package ru.t1murcoder.service;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import ru.t1murcoder.domain.Group;
//import ru.t1murcoder.domain.Student;
//import ru.t1murcoder.domain.Teacher;
//import ru.t1murcoder.repository.GroupRepository;
//import ru.t1murcoder.repository.StudentRepository;
//import ru.t1murcoder.repository.TeacherRepository;
//import ru.t1murcoder.service.impl.GroupServiceImpl;
//import ru.t1murcoder.service.impl.StudentServiceImpl;
//import ru.t1murcoder.service.impl.TeacherServiceImpl;
//
//import java.util.List;
//
//@DataJpaTest
//public class StudentServiceTest {
//    GroupRepository groupRepository;
//
//    TeacherRepository teacherRepository;
//    StudentRepository studentRepository;
//
//    GroupService groupService;
//    TeacherService teacherService;
//    StudentService studentService;
//
//    @Autowired
//    public StudentServiceTest(GroupRepository groupRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
//        this.groupRepository = groupRepository;
//        this.teacherService = new TeacherServiceImpl(teacherRepository, studentRepository);
//        this.teacherRepository = teacherRepository;
//        this.groupService = new GroupServiceImpl(groupRepository, teacherRepository);
//        this.studentRepository = studentRepository;
//        this.studentService = new StudentServiceImpl(studentRepository, groupRepository, teacherRepository);
//    }
//
//
//    @Test
//    void addNewStudent() {
//        Student student = Student.builder()
//                .name("Timur")
//                .username("bebra")
//                .surname("Zar")
//                .build();
//
//        studentService.add(student);
//
//        Student student1 = studentService.getById(1);
//        System.out.println(student1);
//    }
//
//    @Test
//    void addNewStudentToGroup() {
//        Teacher teacher = Teacher.teacherBuilder()
//                .id(1L)
//                .name("Alexander")
//                .username("nepret")
//                .surname("Nepretimov")
//                .password("123")
//                .build();
//
//        teacherService.add(teacher);
//
//        Group group = Group.builder()
//                .name("Bebra1")
//                .teacher(teacher)
//                .build();
//
//        groupService.add(group);
//
//        Student studentTimur = Student.builder()
//                .name("Timur")
//                .username("bebra")
//                .surname("Zar")
//                .group(group)
//                .build();
//
//        Student studentArtem = Student.builder()
//                .name("Artem")
//                .username("zxcxzc")
//                .surname("Dob")
//                .group(group)
//                .build();
//
//        studentService.add(studentTimur);
//        studentService.add(studentArtem);
//
//        Teacher teacher1 = teacherService.getById(1);
//        System.out.println(teacher1);
//
//        Group group1 = groupService.getById(1);
//        System.out.println(group1);
//
//        Student student1 = studentService.getById(1);
//        System.out.println(student1);
//
//        List<Student> studentList = studentService.getByGroup(group);
//        System.out.println(studentList);
//        // TODO: Сделал получение студентов по группе, нужно сделать регу/логин и расписать Dto
//    }
//}
