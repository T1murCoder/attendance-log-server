package ru.t1murcoder.service;

import ru.t1murcoder.controller.dto.GroupDto;
import ru.t1murcoder.controller.dto.GroupWithoutStudentsDto;
import ru.t1murcoder.controller.dto.UserProfileDto;
import ru.t1murcoder.domain.Group;
import ru.t1murcoder.domain.Lesson;
import ru.t1murcoder.domain.Student;
import ru.t1murcoder.domain.Teacher;

import java.util.List;

public interface GroupService {
    GroupDto add(GroupDto groupDto, String teacherUsername);

    List<GroupDto> getAll();

    GroupDto getById(long id);

    GroupDto getByName(String name);

    List<GroupDto> getByTeacherUsername(String username);
    List<GroupWithoutStudentsDto> getByTeacherUsernameWithoutStudents(String username);

    GroupDto getByStudentUsername(String username);

//    GroupDto update(long id, GroupDto GroupDto);

    void deleteById(long id);
}
