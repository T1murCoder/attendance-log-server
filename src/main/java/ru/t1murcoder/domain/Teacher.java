package ru.t1murcoder.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    // Пока сделал Один Препод К Одной Группе, потом поменяю по надобности
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Group> groupList;

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    private List<Lesson> lessonList;
}
