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
@Table(name = "student")
//@ToString(exclude = {"group", "eventList", "lessonsAttendSet"})
//@EqualsAndHashCode(exclude = {"group", "eventList", "lessonsAttendSet"})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    private String password;

    @Column(name = "telegram_url")
    private String telegramUrl;

    @Column(name = "github_url")
    private String githubUrl;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "points")
    private Float points;

    @ManyToOne(targetEntity = Group.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    // вероятно придётся перейти на Set
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Attendance> attendanceList;
}
