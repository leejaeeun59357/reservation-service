package org.smarthammer.reservation.domain.repository;

import java.util.Optional;
import org.smarthammer.reservation.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);
}
