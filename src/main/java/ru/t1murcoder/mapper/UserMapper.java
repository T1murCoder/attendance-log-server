package ru.t1murcoder.mapper;

import lombok.experimental.UtilityClass;
import ru.t1murcoder.controller.dto.UserProfileDto;
import ru.t1murcoder.controller.dto.UserRegisterDto;
import ru.t1murcoder.domain.Student;
import ru.t1murcoder.domain.Teacher;

@UtilityClass
public class UserMapper {

    public UserProfileDto toUserProfileDto(Student student) {

        return UserProfileDto.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .telegramUrl(student.getTelegramUrl())
                .githubUrl(student.getGithubUrl())
                .photoUrl(student.getPhotoUrl())
                .build();
    }

    public UserProfileDto toUserProfileDto(Teacher teacher) {

        return UserProfileDto.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .telegramUrl(teacher.getTelegramUrl())
                .githubUrl(teacher.getGithubUrl())
                .photoUrl(teacher.getPhotoUrl())
                .build();
    }

    public Student toStudentEntity(UserRegisterDto userRegisterDto) {

        Student student = Student.builder()
                .name(userRegisterDto.getName())
                .surname(userRegisterDto.getSurname())
                .login(userRegisterDto.getLogin())
                .password(userRegisterDto.getPassword())
                .build();

        if (userRegisterDto.getId() != null) student.setId(userRegisterDto.getId());

        return student;
    }

    public Student toStudentEntity(UserProfileDto userProfileDto) {

        return Student.builder()
                .id(userProfileDto.getId())
                .name(userProfileDto.getName())
                .surname(userProfileDto.getSurname())
                .telegramUrl(userProfileDto.getTelegramUrl())
                .githubUrl(userProfileDto.getGithubUrl())
                .photoUrl(userProfileDto.getPhotoUrl())
                .build();
    }

    public Teacher toTeacherEntity(UserRegisterDto userRegisterDto) {

        Teacher teacher = Teacher.builder()
                .name(userRegisterDto.getName())
                .surname(userRegisterDto.getSurname())
                .login(userRegisterDto.getLogin())
                .password(userRegisterDto.getPassword())
                .build();

        if (userRegisterDto.getId() != null) teacher.setId(userRegisterDto.getId());

        return teacher;
    }

    public Teacher toTeacherEntity(UserProfileDto userProfileDto) {

        return Teacher.builder()
                .id(userProfileDto.getId())
                .name(userProfileDto.getName())
                .surname(userProfileDto.getSurname())
                .telegramUrl(userProfileDto.getTelegramUrl())
                .githubUrl(userProfileDto.getGithubUrl())
                .photoUrl(userProfileDto.getPhotoUrl())
                .build();
    }

    public UserRegisterDto toUserRegisterDto(Student student) {

        return UserRegisterDto.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .login(student.getLogin())
                .password(student.getPassword())
                .build();
    }

    public UserRegisterDto toUserRegisterDto(Teacher teacher) {

        return UserRegisterDto.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .login(teacher.getLogin())
                .password(teacher.getPassword())
                .build();
    }

}
