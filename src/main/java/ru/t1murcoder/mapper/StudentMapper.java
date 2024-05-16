package ru.t1murcoder.mapper;

import lombok.experimental.UtilityClass;
import ru.t1murcoder.controller.dto.StudentDto;
import ru.t1murcoder.domain.Student;

@UtilityClass
public class StudentMapper {

    public StudentDto toStudentDto(Student student) {

        return StudentDto.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .points(student.getPoints())
                .build();
    }

    public Student toStudentEntity(StudentDto studentDto) {

        Student student = Student.builder()
                .name(studentDto.getName())
                .surname(studentDto.getSurname())
                .points(studentDto.getPoints())
                .build();

        if (studentDto.getId() != null) student.setId(studentDto.getId());

        return student;
    }

}
