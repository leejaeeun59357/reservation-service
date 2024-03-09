package org.smarthammer.reservation.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smarthammer.reservation.Exception.CustomException;
import org.smarthammer.reservation.domain.Form.Reserve.AddReserveForm;
import org.smarthammer.reservation.domain.dto.ReserveDto;
import org.smarthammer.reservation.domain.model.Consumer;
import org.smarthammer.reservation.service.Reserve.ReserveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.smarthammer.reservation.Exception.ErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReserveAddApplication {

    private final ReserveService reserveService;

    @Transactional
    public ReserveDto addReserve(AddReserveForm form) {
        Consumer consumer = reserveService.findConsumer(form.getConsumerEmail())
                .orElseThrow(() -> new CustomException(NOT_FOUND_CONSUMER));

        // 예약을 원하는 시간이 과거 시간일 경우
        if (reserveService.isWantedReserveTimePast(form.getReserveDateAndTime()) ) {
            throw new CustomException(WANT_TIME_IS_PAST);
        }


        // 원하는 시간대에 이미 예약이 존재하는지 확인
        if (reserveService.isReserveTimeExist(form.getReserveDateAndTime())) {
            throw new CustomException(RESERVATION_IS_ALREADY_EXIST);
        }

        return reserveService.saveReserve(form, consumer);
    }
}
