package ru.t1murcoder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lesson")
@ToString
@EqualsAndHashCode
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "date")
    private String date;

    @Column(name = "duration")
    private long duration;

    @ManyToOne(targetEntity = Teacher.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(targetEntity = Schedule.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;


    @JsonIgnoreProperties("lessonsAttendSet")
    @ManyToMany(mappedBy = "lessonsAttendSet", fetch = FetchType.LAZY)
//    @JoinTable(name = "student_lesson_table",
//            joinColumns = @JoinColumn(name = "lesson_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    private Set<Student> attendedStudentSet = new HashSet<>();

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Lesson lesson = (Lesson) o;
//
//        if (id != lesson.id) return false;
//        if (duration != lesson.duration) return false;
//        if (!Objects.equals(date, lesson.date)) return false;
//        if (!Objects.equals(teacher, lesson.teacher)) return false;
//        if (!Objects.equals(schedule, lesson.schedule)) return false;
//        return Objects.equals(attendedStudentSet, lesson.attendedStudentSet);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = (int) (id ^ (id >>> 32));
//        result = 31 * result + (date != null ? date.hashCode() : 0);
//        result = 31 * result + (int) (duration ^ (duration >>> 32));
//        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
//        result = 31 * result + (schedule != null ? schedule.hashCode() : 0);
//        result = 31 * result + (attendedStudentSet != null ? attendedStudentSet.hashCode() : 0);
//        return result;
//    }
}
