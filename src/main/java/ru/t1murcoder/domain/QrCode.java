package ru.t1murcoder.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: добавить EqualsAndHashCode и ToString
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "qr_code")
public class QrCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "qr_value")
    private int value;

    @Column(name = "valid_until")
    private int validUntil;

}
