package org.smarthammer.reservation.domain.repository;

import org.smarthammer.reservation.domain.model.Reserve;
import org.smarthammer.reservation.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByReserve(Reserve reserve);
}
