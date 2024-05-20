package ru.t1murcoder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1murcoder.controller.dto.AttendanceDto;
import ru.t1murcoder.domain.Attendance;
import ru.t1murcoder.domain.Lesson;
import ru.t1murcoder.domain.Student;
import ru.t1murcoder.exception.AttendanceNotFoundException;
import ru.t1murcoder.exception.LessonNotFoundException;
import ru.t1murcoder.exception.UserNotFoundException;
import ru.t1murcoder.mapper.AttendanceMapper;
import ru.t1murcoder.repository.AttendanceRepository;
import ru.t1murcoder.repository.LessonRepository;
import ru.t1murcoder.repository.StudentRepository;
import ru.t1murcoder.repository.TeacherRepository;
import ru.t1murcoder.service.AttendanceService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;

    @Override
    public AttendanceDto add(AttendanceDto attendanceDto) {

        Attendance attendance = AttendanceMapper.toAttendanceEntity(attendanceDto);

        Student student = studentRepository.findById(attendanceDto.getStudentId())
                .orElseThrow(
                        () -> new UserNotFoundException("Student with ID " + attendanceDto.getStudentId() + " not found")
                );

        Lesson lesson = lessonRepository.findById(attendanceDto.getLessonId())
                .orElseThrow(
                        () -> new LessonNotFoundException("Lesson with ID " + attendanceDto.getLessonId() + " not found")
                );

        attendance.setStudent(student);
        attendance.setLesson(lesson);

        return AttendanceMapper.toAttendanceDto(attendanceRepository.save(attendance));
    }

    @Override
    public List<AttendanceDto> getAll() {
        return attendanceRepository.findAll()
                .stream()
                .map(AttendanceMapper::toAttendanceDto)
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceDto getById(long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(
                        () -> new AttendanceNotFoundException("Attendance with ID " + id + " not found")
                );

        return AttendanceMapper.toAttendanceDto(attendance);
    }

    @Override
    public List<AttendanceDto> getByStudentId(long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException("Student with ID " + id + " not found")
                );

        return attendanceRepository.findByStudentId(id)
                .stream()
                .map(AttendanceMapper::toAttendanceDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDto> getByLessonId(long id) {

        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(
                        () -> new LessonNotFoundException("Lesson with ID " + id + " not found")
                );

        return attendanceRepository.findByLessonId(id)
                .stream()
                .map(AttendanceMapper::toAttendanceDto)
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceDto update(long id, AttendanceDto attendanceDto) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(
                        () -> new AttendanceNotFoundException("Attendance with ID " + id + " not found")
                );

        attendance.setIsVisited(attendanceDto.getIsVisited());
        attendance.setPoints(attendanceDto.getPoints());

        return AttendanceMapper.toAttendanceDto(attendanceRepository.save(attendance));
    }

    @Override
    public void deleteById(long id) {
        // TODO: ПРоверить удаление
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(
                        () -> new AttendanceNotFoundException("Attendance with ID " + id + " not found")
                );

        attendanceRepository.deleteById(id);
    }
}
