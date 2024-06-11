package ru.t1murcoder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.t1murcoder.controller.dto.GroupDto;
import ru.t1murcoder.controller.dto.GroupWithoutStudentsDto;
import ru.t1murcoder.controller.dto.StudentDto;
import ru.t1murcoder.service.GroupService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group/")
public class GroupController {
    private final GroupService groupService;

    @PostMapping()
    public GroupDto add(Authentication authentication, @RequestBody GroupDto groupDto) {
        return groupService.add(groupDto, authentication.getName());
    }

    @PutMapping("/{id}")
    public GroupDto update(@PathVariable Long id, @RequestBody GroupDto groupDto) {
        return groupService.update(id, groupDto);
    }

    @GetMapping()
    public List<GroupDto> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/teacher/")
    public List<GroupWithoutStudentsDto> getAllByTeacherUsername(Authentication authentication) {
        return groupService.getByTeacherUsernameWithoutStudents(authentication.getName());
    }

    @GetMapping("/{id}")
    public GroupDto getById(@PathVariable Long id) {
        return groupService.getById(id);
    }

    @GetMapping("/student/{id}")
    public GroupDto getGroupByStudentId(@PathVariable Long id) {
        return groupService.getByStudentId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(Authentication authentication, @PathVariable Long id) {
        groupService.deleteById(id, authentication.getName());
    }

    @DeleteMapping("/{group_id}/student/{student_id}")
    public void removeStudentFromGroup(@PathVariable(name = "group_id") Long groupId, @PathVariable(name = "student_id") Long studentId) {
        groupService.removeStudentFromGroupById(groupId, studentId);
    }

    @PutMapping("/{group_id}/student/")
    public GroupDto addStudentsToGroup(@PathVariable(name = "group_id") Long groupId, @RequestBody List<StudentDto> studentDtoList) {
        return groupService.addStudentToGroupById(groupId, studentDtoList);
    }
}
