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

    // Один Препод Ко многим группам
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Group> groupList;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Lesson> lessonList;
}
