package org.smarthammer.reservation.domain.repository;

import org.smarthammer.reservation.domain.model.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    Optional<Consumer> findByEmail(String email);
}
