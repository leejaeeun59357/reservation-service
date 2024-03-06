package org.smarthammer.reservation.service.consumer;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.domain.SignUpForm;
import org.smarthammer.reservation.domain.model.Consumer;
import org.smarthammer.reservation.domain.repository.ConsumerRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SignUpConsumerService {
    private final ConsumerRepository consumerRepository;

    public Consumer signUp(SignUpForm form) {
        return consumerRepository.save(Consumer.from(form));
    }

    public boolean isEmailExist(String email) {
        return consumerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .isPresent();
    }
}
