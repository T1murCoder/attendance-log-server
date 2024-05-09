package ru.t1murcoder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.t1murcoder.domain.Teacher;
import ru.t1murcoder.exception.UserNotFoundException;
import ru.t1murcoder.repository.TeacherRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    //TODO: тут сделать чтобы искал по логину в студентах и учителях

    private final TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Teacher> byUsername = teacherRepository.findByUsername(username);

        if (byUsername.isEmpty()) throw new UserNotFoundException("User with username " + username + " not found");

        return byUsername.get();
    }
}
