package ru.t1murcoder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.t1murcoder.controller.dto.UserProfileDto;
import ru.t1murcoder.controller.dto.UserRegisterDto;
import ru.t1murcoder.domain.*;
import ru.t1murcoder.exception.UserNotFoundException;
import ru.t1murcoder.mapper.UserMapper;
import ru.t1murcoder.repository.*;
import ru.t1murcoder.service.TeacherService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserProfileDto add(UserRegisterDto userRegisterDto) {

        if (userRepository.findByUsername(userRegisterDto.getUsername()).isPresent())
            throw new RuntimeException("Login is already occupied");

        Optional<Authority> optionalAuthority = authorityRepository.findByAuthority("ROLE_TEACHER");
        if (optionalAuthority.isEmpty()) throw new RuntimeException("Authority not found");

        Teacher teacher = UserMapper.toTeacherEntity(userRegisterDto);
        teacher.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        Set<Authority> authoritySet = new HashSet<>();
        authoritySet.add(optionalAuthority.get());
        teacher.setAuthorities(authoritySet);

        Teacher save = teacherRepository.save(teacher);

        return UserMapper.toUserProfileDto(save);
    }

    @Override
    public List<UserProfileDto> getAll() {
        List<Teacher> teacherList = teacherRepository.findAll();
        return teacherList.stream()
                .map(UserMapper::toUserProfileDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserProfileDto getById(long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if (teacher.isEmpty())
            throw new UserNotFoundException("Teacher not found");

        return UserMapper.toUserProfileDto(teacher.get());
    }

    @Override
    public UserProfileDto getByUsername(String username) {
        Optional<Teacher> teacher = teacherRepository.findByUsername(username);

        if (teacher.isEmpty())
            throw new UserNotFoundException("Teacher not found");

        return UserMapper.toUserProfileDto(teacher.get());
    }

    @Override
    public UserProfileDto checkUsernameIsPresent(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty())
            throw new UserNotFoundException("User with name " + username + " not found");

        return UserMapper.toUserProfileDto(userOptional.get());
    }

    @Override
    public UserProfileDto update(long id, UserProfileDto userProfileDto) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);

        if (teacherOptional.isEmpty())
            throw new UserNotFoundException("Teacher with ID " + id + " not found");

        Teacher teacher = teacherOptional.get();
        if (userProfileDto.getName() != null) teacher.setName(userProfileDto.getName());
        if (userProfileDto.getSurname() != null) teacher.setSurname(userProfileDto.getSurname());
        if (userProfileDto.getTelegramUrl() != null) teacher.setTelegramUrl(userProfileDto.getTelegramUrl());
        if (userProfileDto.getGithubUrl() != null) teacher.setGithubUrl(userProfileDto.getGithubUrl());
        if (userProfileDto.getPhotoUrl() != null) teacher.setPhotoUrl(userProfileDto.getPhotoUrl());

        return UserMapper.toUserProfileDto(teacherRepository.save(teacher));
    }

    @Override
    public void deleteById(long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Teacher with ID " + id + " not found"));

//        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
//        if (teacherOptional.isEmpty())
//            throw new RuntimeException("Teacher with ID " + id + " not found");

        teacherRepository.deleteById(id);
    }
}