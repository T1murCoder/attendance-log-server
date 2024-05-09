package ru.t1murcoder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.t1murcoder.controller.dto.UserProfileDto;
import ru.t1murcoder.controller.dto.UserRegisterDto;
import ru.t1murcoder.domain.Teacher;
import ru.t1murcoder.service.TeacherService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher/")
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("/register")
    public UserProfileDto add(@RequestBody UserRegisterDto userRegisterDto) {
        return teacherService.add(userRegisterDto);
    }

    @GetMapping("/login")
    public UserProfileDto login(Authentication authentication) {
        return teacherService.getByUsername(authentication.getName());
    }

    @GetMapping()
    public List<UserProfileDto> getAll() {
        return teacherService.getAll();
    }

    @GetMapping("/{id}")
    public UserProfileDto getById(@PathVariable Long id) {
        return teacherService.getById(id);
    }

    @PutMapping("/{id}")
    public UserProfileDto update(@PathVariable Long id, @RequestBody UserProfileDto userProfileDto) {
        return teacherService.update(id, userProfileDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        teacherService.deleteById(id);
    }

    @GetMapping("/username/{username}")
    public String getByUsername(@PathVariable String username) {
        UserProfileDto byUsername = teacherService.checkUsernameIsPresent(username);

        return "User " + byUsername.getUsername() + " is registered";
    }


}
