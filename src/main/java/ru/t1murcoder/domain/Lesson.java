package ru.t1murcoder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lesson")
//@ToString(exclude = {"teacher", "schedule", "attendedStudentSet"})
@EqualsAndHashCode(exclude = {"group", "attendanceList"})
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "theme")
    private String theme;

    @Column(name = "time_start")
    private GregorianCalendar timeStart;

    @Column(name = "time_end")
    private GregorianCalendar timeEnd;

    @Column(name = "date")
    private GregorianCalendar date;

    @ManyToOne(targetEntity = Group.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<QRCode> qrCodeList;

    // вероятно придётся перейти на Set
    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Attendance> attendanceList;
}
