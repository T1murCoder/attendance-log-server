package ru.t1murcoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.t1murcoder.domain.Attendance;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentId(Long id);
    List<Attendance> findByLessonId(Long id);
    Optional<Attendance> findByLessonIdAndStudentId(Long lessonId, Long studentId);
}
