package ru.t1murcoder.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.t1murcoder.domain.Group;
import ru.t1murcoder.domain.Teacher;
import ru.t1murcoder.service.GroupService;
import ru.t1murcoder.service.TeacherService;
import ru.t1murcoder.service.impl.GroupServiceImpl;
import ru.t1murcoder.service.impl.TeacherServiceImpl;

import java.util.List;

@DataJpaTest
public class GroupServiceTest {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    TeacherRepository teacherRepository;

    GroupService groupService;
    TeacherService teacherService;

    @Test
    void addNewGroupTest() {

        groupService = new GroupServiceImpl(groupRepository, teacherRepository);
        teacherService = new TeacherServiceImpl(teacherRepository);

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

        System.out.println(groupList);

        List<Teacher> teacherList = teacherService.getAll();

        System.out.println(teacherList);

    }
}
