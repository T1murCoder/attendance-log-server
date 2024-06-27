package ru.t1murcoder.mapper;

import lombok.experimental.UtilityClass;
import ru.t1murcoder.controller.dto.StudentDto;
import ru.t1murcoder.controller.dto.StudentWithAttendancesDto;
import ru.t1murcoder.domain.Attendance;
import ru.t1murcoder.domain.Student;

import java.util.stream.Collectors;

@UtilityClass
public class StudentMapper {

    public StudentDto toStudentDto(Student student) {

        StudentDto studentDto = StudentDto.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .username(student.getUsername())
                .build();

        if (student.getAttendanceList() != null) {
            studentDto.setPoints(
                    student.getAttendanceList()
                            .stream()
                            .mapToInt(Attendance::getPoints)
                            .sum()
            );
        }


        return studentDto;
    }

    public StudentWithAttendancesDto toStudentWithAttendancesDto(Student student) {

        StudentWithAttendancesDto studentDto = StudentWithAttendancesDto.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .photoUrl(student.getPhotoUrl())
                .build();

        if (student.getAttendanceList() != null) {
            studentDto.setAttendanceDtoList(student.getAttendanceList()
                    .stream()
                    .map(AttendanceMapper::toAttendanceDto)
                    .collect(Collectors.toList())
            );
            studentDto.setPoints(
                    student.getAttendanceList()
                            .stream()
                            .mapToInt(Attendance::getPoints)
                            .sum()
            );
        }

        return studentDto;
    }

    public Student toStudentEntity(StudentDto studentDto) {

        Student student = Student.builder()
                .name(studentDto.getName())
                .surname(studentDto.getSurname())
                .username(studentDto.getUsername())
                .build();

        if (studentDto.getId() != null) student.setId(studentDto.getId());

        return student;
    }

    public Student toStudentEntity(StudentWithAttendancesDto studentDto) {
        Student student = Student.builder()
                .name(studentDto.getName())
                .surname(studentDto.getSurname())
                .photoUrl(studentDto.getPhotoUrl())
                .build();

        if (studentDto.getId() != null) student.setId(studentDto.getId());

        return student;
    }

}
