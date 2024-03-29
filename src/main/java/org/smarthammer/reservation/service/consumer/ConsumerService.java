package org.smarthammer.reservation.service.consumer;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.domain.Form.user.SignUpForm;
import org.smarthammer.reservation.domain.dto.ConsumerDto;
import org.smarthammer.reservation.domain.model.Consumer;
import org.smarthammer.reservation.domain.repository.ConsumerRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsumerService {
    private final ConsumerRepository consumerRepository;

    /**
     * 회원가입 하는 메서드
     *
     * @param form 회원 가입 form
     * @return Consumer
     */
    public Consumer signUp(SignUpForm form) {
        return consumerRepository.save(ConsumerDto.formToEntity(form));
    }

    /**
     * 이미 존재하는 이메일인지 검사하는 메서드
     *
     * @param email
     * @return
     */

    public boolean isEmailExist(String email) {
        return consumerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .isPresent();
    }

}
