package ru.t1murcoder.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "group_id")
    @PrimaryKeyJoinColumn
    private long groupId;

    @Column(name = "lesson_id")
    @OneToMany(mappedBy = "lesson")
    private long[] lessonId;

}
