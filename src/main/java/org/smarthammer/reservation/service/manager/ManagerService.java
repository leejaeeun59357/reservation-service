package org.smarthammer.reservation.service.manager;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.domain.Form.user.SignUpForm;
import org.smarthammer.reservation.domain.dto.ManagerDto;
import org.smarthammer.reservation.domain.model.Manager;
import org.smarthammer.reservation.domain.model.Reserve;
import org.smarthammer.reservation.domain.repository.ManagerRepository;
import org.smarthammer.reservation.domain.repository.ReserveRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final ReserveRepository reserveRepository;

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
     * email을 통해 manager을 찾는 메서드
     * @param email
     * @return
     */
    public Optional<Manager> findManager(String email) {
        return managerRepository.findByEmail(email);
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

    /**
     * 주어진 이름과 예약 시간으로 예약 내역 조회 하는 메서드
     *
     * @param name
     * @param reserveTime
     * @return
     */
    public Optional<Reserve> findReservation(String name, LocalDateTime reserveTime) {
        Optional<Reserve> reservation = reserveRepository.findByNameAndDT(name, reserveTime);

        return reservation;
    }

}
