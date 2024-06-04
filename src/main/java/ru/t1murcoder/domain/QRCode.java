package ru.t1murcoder.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.GregorianCalendar;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "qr_code")
//@ToString(exclude = {"lesson"})
@EqualsAndHashCode(exclude = {"lesson"})
public class QRCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private GregorianCalendar createdAt;

    @Column(name = "expires_at")
    private GregorianCalendar expiresAt;

    @ManyToOne(targetEntity = Lesson.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    public static int qrCodeLifeTime = 30;
}
