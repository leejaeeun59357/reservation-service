package org.smarthammer.reservation.service.Reserve;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smarthammer.reservation.domain.dto.ReserveDto;
import org.smarthammer.reservation.domain.model.Reserve;
import org.smarthammer.reservation.domain.repository.ReserveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KioskService {

    private final ReserveRepository reserveRepository;

    /**
     * 이름과 예약한 시간을 통해 예약 확인
     *
     * @param name
     * @param reserveTime
     * @return
     */
    public Optional<Reserve> findReserveConsumer(String name, LocalDateTime reserveTime) {
        Optional<Reserve> reserve = reserveRepository.findByNameAndDT(name, reserveTime);

        return reserve;
    }


    /**
     * 예약 시간보다 10 분전인 mustArriveTime 시간을 변수에 저장
     * 만약 현재 시간이 mustArriveTime 보다 과거라면 true 변환
     *
     * @param reserve
     * @return boolean
     */
    public boolean isBeforeTenMin(Reserve reserve) {
        LocalDateTime mustArriveTime = reserve.getDT().minusMinutes(10);

        return LocalDateTime.now().isBefore(mustArriveTime);
    }


}