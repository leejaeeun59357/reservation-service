package org.smarthammer.reservation.service.manager;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.domain.SignUpForm;
import org.smarthammer.reservation.domain.model.Manager;
import org.smarthammer.reservation.domain.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SignUpManagerService {
    private final ManagerRepository managerRepository;

    public Manager signUp(SignUpForm form) {
        return managerRepository.save(Manager.from(form));
    }

    public boolean isEmailExist(String email) {
        return managerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .isPresent();
    }
}
