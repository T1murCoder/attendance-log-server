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
@ToString(exclude = {"group", "eventList", "lessonsAttendSet"})
@EqualsAndHashCode(exclude = {"group", "eventList", "lessonsAttendSet"})
public class Student {

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

    @ManyToOne(targetEntity = Group.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "event_id")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<StudentEvent> eventList;

    @JsonIgnoreProperties("attendedStudentSet")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_lesson_table",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id", referencedColumnName = "id"))
    private Set<Lesson> lessonsAttendSet = new HashSet<>();

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Student student = (Student) o;
//
//        if (id != student.id) return false;
//        if (!Objects.equals(name, student.name)) return false;
//        if (!Objects.equals(login, student.login)) return false;
//        if (!Objects.equals(password, student.password)) return false;
//        if (!Objects.equals(group, student.group)) return false;
//        if (!Objects.equals(eventList, student.eventList)) return false;
//        return Objects.equals(lessonsAttendSet, student.lessonsAttendSet);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = (int) (id ^ (id >>> 32));
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (login != null ? login.hashCode() : 0);
//        result = 31 * result + (password != null ? password.hashCode() : 0);
//        result = 31 * result + (group != null ? group.hashCode() : 0);
//        result = 31 * result + (eventList != null ? eventList.hashCode() : 0);
//        result = 31 * result + (lessonsAttendSet != null ? lessonsAttendSet.hashCode() : 0);
//        return result;
//    }
}
