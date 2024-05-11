package ru.t1murcoder.service;

import ru.t1murcoder.controller.dto.UserProfileDto;
import ru.t1murcoder.controller.dto.UserRegisterDto;
import ru.t1murcoder.domain.Teacher;

import java.util.List;

public interface TeacherService {
    UserProfileDto add(UserRegisterDto teacher);

    List<UserProfileDto> getAll();

    UserProfileDto getById(long id);

    UserProfileDto getByUsername(String username);
    //TODO: Сделать метод получения по группам
    UserProfileDto checkUsernameIsPresent(String username);

    UserProfileDto update(long id, UserProfileDto userProfileDto);

    void deleteById(long id);
}
