package ru.t1murcoder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.t1murcoder.controller.dto.UserProfileDto;
import ru.t1murcoder.controller.dto.UserRegisterDto;
import ru.t1murcoder.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student/")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/register")
    public UserProfileDto add(@RequestBody UserRegisterDto userRegisterDto) {
        return studentService.add(userRegisterDto);
    }

    @GetMapping("/login")
    public UserProfileDto login(Authentication authentication) {
        return studentService.getByUsername(authentication.getName());
    }

    @GetMapping()
    public List<UserProfileDto> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public UserProfileDto getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PutMapping("/{id}")
    public UserProfileDto update(@PathVariable Long id, @RequestBody UserProfileDto userProfileDto) {
        return studentService.update(id, userProfileDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }

    @GetMapping("/username/{username}")
    public String getByUsername(@PathVariable String username) {
        UserProfileDto byUsername = studentService.checkUsernameIsPresent(username);

        return "User " + byUsername.getUsername() + " is registered";
    }

}
