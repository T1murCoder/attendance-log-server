package ru.t1murcoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.t1murcoder.domain.Group;
import ru.t1murcoder.domain.Teacher;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByName(String name);
    Optional<Group> findByTeacher(Teacher teacher);
}
