package ru.t1murcoder.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"group", "lessonList"})
@ToString(exclude = {"group", "lessonList"})
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne(targetEntity = Group.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "lesson_id")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule")
    private List<Lesson> lessonList;

}
