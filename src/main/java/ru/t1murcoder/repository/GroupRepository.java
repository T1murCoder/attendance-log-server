package ru.t1murcoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.t1murcoder.domain.Group;
import ru.t1murcoder.domain.Student;
import ru.t1murcoder.domain.Teacher;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByName(String name);
    List<Group> findByTeacher(Teacher teacher);
    Optional<Group> findByStudentListContains(Student student);
    @Modifying
    @Query("DELETE FROM Group g WHERE g.id = :id")
    void deleteById(long id);
    @Modifying
    @Query("UPDATE Student s SET s.group = null WHERE s.group.id = :id")
    void deleteStudentRelationByGroupId(long id);

    @Modifying
    @Query("DELETE FROM Attendance a WHERE a.lesson.group.id = :id")
    void deleteAttendancesByGroupId(long id);

    @Modifying
    @Query("DELETE FROM Lesson l WHERE l.group.id = :id")
    void deleteLessonsByGroupId(long id);

    @Modifying
    @Query("DELETE FROM QRCode q WHERE q.lesson.group.id = :id")
    void deleteQRCodesByGroupId(long id);
}
