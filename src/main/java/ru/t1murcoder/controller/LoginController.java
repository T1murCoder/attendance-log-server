package ru.t1murcoder.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.t1murcoder.controller.dto.LoginDto;
import ru.t1murcoder.controller.dto.UserProfileDto;
import ru.t1murcoder.controller.dto.UserRegisterDto;

@RestController
public class LoginController {

    @PostMapping("/teacher/register")
    public void register(@RequestBody UserRegisterDto userData) {}

    @PostMapping("/teacher/login")
    public UserProfileDto login(@RequestBody LoginDto userData) {
        //TODO: Добавить спринг секьюрити
        //TODO: перейти на spring 2.5.5
        return null;
    }

}
