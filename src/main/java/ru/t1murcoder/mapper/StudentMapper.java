package ru.t1murcoder.mapper;

import lombok.experimental.UtilityClass;
import ru.t1murcoder.controller.dto.StudentDto;
import ru.t1murcoder.controller.dto.StudentWithAttendancesDto;
import ru.t1murcoder.domain.Student;

import java.util.stream.Collectors;

@UtilityClass
public class StudentMapper {

    public StudentDto toStudentDto(Student student) {

        return StudentDto.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .username(student.getUsername())
                .points(student.getPoints())
                .build();
    }

    public StudentWithAttendancesDto toStudentWithAttendancesDto(Student student) {

        StudentWithAttendancesDto studentDto = StudentWithAttendancesDto.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .points(student.getPoints())
                .build();

        if (student.getAttendanceList() != null) {
            studentDto.setAttendanceDtoList(student.getAttendanceList()
                    .stream()
                    .map(AttendanceMapper::toAttendanceDto)
                    .collect(Collectors.toList())
            );
        }


        return studentDto;
    }

    public Student toStudentEntity(StudentDto studentDto) {

        Student student = Student.builder()
                .name(studentDto.getName())
                .surname(studentDto.getSurname())
                .username(studentDto.getUsername())
                .points(studentDto.getPoints())
                .build();

        if (studentDto.getId() != null) student.setId(studentDto.getId());

        return student;
    }

    public Student toStudentEntity(StudentWithAttendancesDto studentDto) {
        Student student = Student.builder()
                .name(studentDto.getName())
                .surname(studentDto.getSurname())
                .points(studentDto.getPoints())
                .build();

        if (studentDto.getId() != null) student.setId(studentDto.getId());

        return student;
    }

}
