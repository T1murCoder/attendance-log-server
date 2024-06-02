package ru.t1murcoder.service;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
    GroupDto getByStudentId(Long id);

    GroupDto update(long id, GroupDto groupDto);

    void deleteById(long id, String username);
}
