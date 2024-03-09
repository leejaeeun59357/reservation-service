package org.smarthammer.reservation.domain.repository;

import org.smarthammer.reservation.domain.model.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Long> {
    Optional<Reserve> findByDT(LocalDateTime wantToReserveTime);
}
