package org.smarthammer.reservation.service.manager;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.domain.Form.user.SignUpForm;
import org.smarthammer.reservation.domain.dto.ManagerDto;
import org.smarthammer.reservation.domain.model.Manager;
import org.smarthammer.reservation.domain.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;

    /**
     * Controller에서 form을 받아 repository에 저장
     *
     * @param form
     * @return Manager
     */
    public Manager signUp(SignUpForm form) {
        return managerRepository.save(ManagerDto.formToEntity(form));
    }

    /**
     * 이미 존재하는 이메일인지 검사하는 메서드
     *
     * @param email
     * @return boolean
     */
    public boolean isEmailExist(String email) {
        return managerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .isPresent();
    }

//    // 로그인 시 이메일과 패스워드가 일치하고
//    // 파트너십이 체결되어있는지 검사
//    public Optional<Manager> findByEmailAndPassword(String email, String password) {
//        return managerRepository.findByEmail(email).stream()
//                .filter(manager -> manager.getEmail().equals(email)
//                && manager.getPassword().equals(password)
//                && manager.getPartnership()).findFirst();
//    }
}
