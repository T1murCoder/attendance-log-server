package ru.t1murcoder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

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
