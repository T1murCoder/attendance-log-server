package ru.t1murcoder.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.t1murcoder.controller.dto.LoginDto;
import ru.t1murcoder.controller.dto.UserProfileDto;
import ru.t1murcoder.controller.dto.UserRegisterDto;

@RestController
public class LoginController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello!!!";
    }

    @GetMapping("/hello_auth")
    public String helloAuthorized() {
        return "You are Authorized!";
    }

//    @PostMapping("/teacher/register")
//    public void register(@RequestBody UserRegisterDto userData) {}
//
//    @PostMapping("/teacher/login")
//    public UserProfileDto login(@RequestBody LoginDto userData) {
//        return null;
//    }

}
