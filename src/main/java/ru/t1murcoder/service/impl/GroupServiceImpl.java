package ru.t1murcoder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.t1murcoder.controller.dto.GroupDto;
import ru.t1murcoder.domain.Group;
import ru.t1murcoder.domain.Lesson;
import ru.t1murcoder.domain.Student;
import ru.t1murcoder.domain.Teacher;
import ru.t1murcoder.repository.GroupRepository;
import ru.t1murcoder.repository.TeacherRepository;
import ru.t1murcoder.service.GroupService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public Group add(Group group) {
        Teacher teacher = group.getTeacher();
        if (teacherRepository.findById(teacher.getId()).isEmpty())
            throw new RuntimeException("Teacher not found");

        if (group.getName() == null)
            throw new RuntimeException("Group must have name");

        if (groupRepository.findByName(group.getName()).isPresent())
            throw new RuntimeException("Group already exists");

        return groupRepository.save(group);
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group getById(long id) {
        Optional<Group> group = groupRepository.findById(id);

        if (group.isEmpty())
            throw new RuntimeException("Group not found");

        return group.get();
    }

    @Override
    public Group getByName(String name) {
        Optional<Group> group = groupRepository.findByName(name);

        if (group.isEmpty())
            throw new RuntimeException("Group not found");

        return group.get();
    }

    @Override
    public Group getByTeacher(Teacher teacher) {
        Optional<Group> group = groupRepository.findByTeacher(teacher);

        if (group.isEmpty())
            throw new RuntimeException("Group not found");

        return group.get();
    }

    @Override
    public Group addStudent(long id, Student student) {

        Group group = getById(id);

        // TODO: ЗДЕСЬ СДЕЛАТЬ МЕТОД ДОБАВЛЕНИЯ СТУДЕНТА В ГРУППУ

        return null;
    }

    @Override
    public Group addLesson(long id, Lesson lesson) {
        return null;
    }

    @Override
    public Group update(long id, Group group) {
//        Optional<Group> groupOptional = groupRepository.findById(id);
//
//        if (groupOptional.isEmpty()) throw new RuntimeException("Group with ID " + id + " not found");
//
//        Group newGroup = groupOptional.get();
//        newGroup.

        return null;
    }

    @Override
    public void deleteById(long id) {
        groupRepository.deleteById(id);
    }
}
