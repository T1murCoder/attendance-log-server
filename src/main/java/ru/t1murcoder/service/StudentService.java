package ru.t1murcoder.service;

import ru.t1murcoder.controller.dto.StudentDto;
import ru.t1murcoder.controller.dto.StudentWithAttendancesDto;
import ru.t1murcoder.controller.dto.UserProfileDto;
import ru.t1murcoder.controller.dto.UserRegisterDto;
import ru.t1murcoder.domain.Group;
import ru.t1murcoder.domain.Student;

import java.util.List;

public interface StudentService {
    UserProfileDto add(UserRegisterDto student);

    List<UserProfileDto> getAll();

    UserProfileDto getById(long id);

    List<StudentDto> getVacantStudents();

    UserProfileDto getByUsername(String username);

    List<StudentWithAttendancesDto> getByGroupId(long id);

    UserProfileDto checkUsernameIsPresent(String username);

    UserProfileDto update(long id, UserProfileDto userProfileDto);

    void deleteById(long id);
}
