package ru.t1murcoder.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.t1murcoder.domain.Group;
import ru.t1murcoder.domain.Teacher;
import ru.t1murcoder.repository.GroupRepository;
import ru.t1murcoder.repository.TeacherRepository;
import ru.t1murcoder.service.impl.GroupServiceImpl;
import ru.t1murcoder.service.impl.TeacherServiceImpl;

@DataJpaTest
public class TeacherServiceTest {

    GroupRepository groupRepository;

    TeacherRepository teacherRepository;

    GroupService groupService;
    TeacherService teacherService;

    @Autowired
    public TeacherServiceTest(GroupRepository groupRepository, TeacherRepository teacherRepository) {
        this.groupRepository = groupRepository;
        this.teacherService = new TeacherServiceImpl(teacherRepository);
        this.teacherRepository = teacherRepository;
        this.groupService = new GroupServiceImpl(groupRepository, teacherRepository);
    }

    @Test
    void addNewTeacherAndGetTest() {
//        teacherService = new TeacherServiceImpl(teacherRepository);

        Teacher teacher = Teacher.builder()
//                .id(1L)
                .name("Alexander")
                .login("nepret")
                .surname("Nepretimov")
                .password("123")
                .build();

        teacherService.add(teacher);

        Group group = Group.builder()
                .id(1L)
                .name("Bebra")
                .teacher(teacher)
                .build();

        groupService.add(group);

        Teacher teacher1 = teacherService.getByLogin("nepret");



        System.out.println(teacher1);
    }

    @Test
    void addNewGroupToTeacher() {
//        groupService = new GroupServiceImpl(groupRepository, teacherRepository);

//        Group group =
    }

    @Test
    void updateTeacherTest() {
        Teacher teacher = Teacher.builder()
                .name("Alexander")
                .login("nepret")
                .surname("Nepretimov")
                .password("123")
                .build();

        teacherService.add(teacher);

        System.out.println(teacherService.getById(1));

        Teacher newTeacher = Teacher.builder()
                .name("Sasha")
                .login("nepret")
                .surname("Nepretimov")
                .password("123")
                .build();

        teacherService.update(1, newTeacher);

        Teacher newTeacher1 = teacherService.getById(1);

        System.out.println(newTeacher1.getGroupList());
    }

}
