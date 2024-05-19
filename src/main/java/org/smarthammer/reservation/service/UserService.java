package org.smarthammer.reservation.service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.domain.Form.user.SignUpForm;
import org.smarthammer.reservation.domain.dto.UserDto;
import org.smarthammer.reservation.domain.model.Reserve;
import org.smarthammer.reservation.domain.model.Type;
import org.smarthammer.reservation.domain.model.User;
import org.smarthammer.reservation.domain.repository.ReserveRepository;
import org.smarthammer.reservation.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final ReserveRepository reserveRepository;

  public User signUp(SignUpForm form, Type type) {

    if(type == Type.MANAGER) {
      User user = User.formToEntity(form, type);
      user.setPartnership(true);
      return userRepository.save(user);
    } else {
      return userRepository.save(User.formToEntity(form, type));
    }
  }


  public boolean isEmailExist(String email) {
    return userRepository.findByEmail(email.toLowerCase(Locale.ROOT))
        .isPresent();
  }

  public Optional<Reserve> findReservation(String name, LocalDateTime reserveTime) {
    Optional<Reserve> reservation = reserveRepository.findByNameAndDT(name, reserveTime);

    return reservation;
  }

  public Optional<User> findUser(String email) {
    return userRepository.findByEmail(email);
  }
}
