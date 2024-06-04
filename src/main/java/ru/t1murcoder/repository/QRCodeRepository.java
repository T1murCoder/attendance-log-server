package ru.t1murcoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.t1murcoder.domain.QRCode;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
}
