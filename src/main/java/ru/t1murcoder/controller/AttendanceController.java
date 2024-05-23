package ru.t1murcoder.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.t1murcoder.controller.dto.AttendanceDto;
import ru.t1murcoder.service.AttendanceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance/")
public class AttendanceController {

    //TODO: Расписать эндпоинты и отправить Артёму
    //TODO: Сделать получение по айди урока и айди студента
    //TODO: Подумать над предоставлением данных для таблицы

    private final AttendanceService attendanceService;

    @PostMapping()
    public AttendanceDto add(@RequestBody AttendanceDto attendanceDto) {
        return attendanceService.add(attendanceDto);
    }

    @PutMapping("/{id}")
    public AttendanceDto update(@PathVariable Long id, @RequestBody AttendanceDto attendanceDto) {
        return attendanceService.update(id, attendanceDto);
    }

    @GetMapping()
    public List<AttendanceDto> getAll() {
        return attendanceService.getAll();
    }

    @GetMapping("/student/{id}")
    public List<AttendanceDto> getByStudentId(@PathVariable Long id) {
        return attendanceService.getByStudentId(id);
    }

    @GetMapping("/{id}")
    public AttendanceDto getById(@PathVariable Long id) {
        return attendanceService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        attendanceService.deleteById(id);
    }
}
