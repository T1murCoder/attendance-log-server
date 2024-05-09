package ru.t1murcoder.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.t1murcoder.domain.Group;
import ru.t1murcoder.domain.Teacher;
import ru.t1murcoder.repository.GroupRepository;
import ru.t1murcoder.repository.TeacherRepository;
import ru.t1murcoder.service.GroupService;
import ru.t1murcoder.service.TeacherService;
import ru.t1murcoder.service.impl.GroupServiceImpl;
import ru.t1murcoder.service.impl.TeacherServiceImpl;

import java.util.List;

@DataJpaTest
public class GroupServiceTest {

    GroupRepository groupRepository;

    TeacherRepository teacherRepository;

    GroupService groupService;
    TeacherService teacherService;

    @Autowired
    public GroupServiceTest(GroupRepository groupRepository, TeacherRepository teacherRepository) {
        this.groupRepository = groupRepository;
        this.teacherService = new TeacherServiceImpl(teacherRepository);
        this.teacherRepository = teacherRepository;
        this.groupService = new GroupServiceImpl(groupRepository, teacherRepository);
    }

    @Test
    void addNewGroupTest() {

        Teacher teacher = Teacher.builder()
                .id(1L)
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

        List<Group> groupList = groupService.getAll();
        List<Group> groupByTeacher = groupService.getByTeacher(teacher);

        System.out.println(groupByTeacher);

        List<Teacher> teacherList = teacherService.getAll();
        Teacher teacher1 = teacherService.getByLogin("nepret");

        List<Group> groupByTeacher2 = groupService.getByTeacher(teacher1);

        System.out.println(groupByTeacher2);

//        assert group1 == group2;

        System.out.println(teacher1);

    }

    @Test
    void addTwoGroupsToOneTeacher() {
        Teacher teacher = Teacher.builder()
                .id(1L)
                .name("Alexander")
                .login("nepret")
                .surname("Nepretimov")
                .password("123")
                .build();

        teacherService.add(teacher);

        Group group1 = Group.builder()
                .name("Bebra1")
                .teacher(teacher)
                .build();

        Group group2 = Group.builder()
                .name("Bebra2")
                .teacher(teacher)
                .build();

        groupService.add(group1);
        groupService.add(group2);

        List<Group> groupList = groupService.getByTeacher(teacher);

        System.out.println(groupList);

    }
}
