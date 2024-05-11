package ru.t1murcoder.mapper;

import lombok.experimental.UtilityClass;
import ru.t1murcoder.controller.dto.UserProfileDto;
import ru.t1murcoder.controller.dto.UserRegisterDto;
import ru.t1murcoder.domain.Student;
import ru.t1murcoder.domain.Teacher;
import ru.t1murcoder.domain.User;

@UtilityClass
public class UserMapper {

    public UserProfileDto toUserProfileDto(Student student) {

        return UserProfileDto.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .username(student.getUsername())
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
                .username(teacher.getUsername())
                .telegramUrl(teacher.getTelegramUrl())
                .githubUrl(teacher.getGithubUrl())
                .photoUrl(teacher.getPhotoUrl())
                .build();
    }

    public UserProfileDto toUserProfileDto(User user) {

        return UserProfileDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

    public Student toStudentEntity(UserRegisterDto userRegisterDto) {

        Student student = Student.builder()
                .name(userRegisterDto.getName())
                .surname(userRegisterDto.getSurname())
                .username(userRegisterDto.getUsername())
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
                .username(userProfileDto.getUsername())
                .telegramUrl(userProfileDto.getTelegramUrl())
                .githubUrl(userProfileDto.getGithubUrl())
                .photoUrl(userProfileDto.getPhotoUrl())
                .build();
    }

    public User toUserEntity(UserProfileDto userProfileDto) {

        return User.builder()
                .id(userProfileDto.getId())
                .username(userProfileDto.getUsername())
                .build();
    }

    public Teacher toTeacherEntity(UserRegisterDto userRegisterDto) {

        Teacher teacher = Teacher.builder()
                .name(userRegisterDto.getName())
                .surname(userRegisterDto.getSurname())
                .username(userRegisterDto.getUsername())
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
                .username(userProfileDto.getUsername())
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
                .username(student.getUsername())
                .password(student.getPassword())
                .build();
    }

    public UserRegisterDto toUserRegisterDto(Teacher teacher) {

        return UserRegisterDto.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .username(teacher.getUsername())
                .password(teacher.getPassword())
                .build();
    }

    public User toUserEntity(UserRegisterDto userRegisterDto) {

        User user = User.builder()
                .username(userRegisterDto.getUsername())
                .password(userRegisterDto.getPassword())
                .build();

        if (userRegisterDto.getId() != null) user.setId(userRegisterDto.getId());

        return user;
    }

}
