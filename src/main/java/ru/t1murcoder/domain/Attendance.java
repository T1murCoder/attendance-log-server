package ru.t1murcoder.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendance")
@EqualsAndHashCode(exclude = {"student", "lesson"})
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_visited")
    private Boolean isVisited;

    @Column(name = "points")
    private Integer points;

    @JoinColumn(name = "student_id")
    @ManyToOne(targetEntity = Student.class, fetch = FetchType.LAZY)
    private Student student;

    @JoinColumn(name = "lesson_id")
    @ManyToOne(targetEntity = Lesson.class, fetch = FetchType.LAZY)
    private Lesson lesson;
}
