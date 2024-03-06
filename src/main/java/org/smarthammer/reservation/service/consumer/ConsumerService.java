package org.smarthammer.reservation.service.consumer;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.domain.SignUpForm;
import org.smarthammer.reservation.domain.model.Consumer;
import org.smarthammer.reservation.domain.repository.ConsumerRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsumerService {
    private final ConsumerRepository consumerRepository;

    public Consumer signUp(SignUpForm form) {
        return consumerRepository.save(Consumer.from(form));
    }

    public boolean isEmailExist(String email) {
        return consumerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .isPresent();
    }

    // 로그인 시 이메일과 패스워드가 일치하는지 검사
    public Optional<Consumer> findByEmailAndPassword(String email, String password) {
        return consumerRepository.findByEmail(email).stream()
                .filter(consumer -> consumer.getEmail().equals(email) && consumer.getPassword().equals(password))
                .findFirst();
    }
}
