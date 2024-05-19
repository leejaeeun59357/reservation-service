package org.smarthammer.reservation.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smarthammer.reservation.Exception.CustomException;
import org.smarthammer.reservation.domain.Form.Reserve.AddReserveForm;
import org.smarthammer.reservation.domain.dto.ReserveDto;
import org.smarthammer.reservation.domain.model.Store;
import org.smarthammer.reservation.domain.model.User;
import org.smarthammer.reservation.service.Reserve.ReserveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.smarthammer.reservation.Exception.ErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReserveAddApplication {

    private final ReserveService reserveService;

    /**
     * controller에서 입력받은 form으로
     * 1. email을 이용하여 해당 회원이 회원가입된 회원인지
     * 2. 예약하고자 하는 store이 등록된 store인지
     * 3. 예약하고자 하는 시간이 현재보다 과거인지
     * 4. 원하는 시간에 이미 예약이 존재하는지 검사 한다.
     *
     * 이후 이상이 없으면 저장
     * @param form
     * @return ReserveDto
     */
    @Transactional
    public ReserveDto addReserve(AddReserveForm form) {
        // 1. email을 이용하여 해당 회원이 회원가입된 회원인지
        User user = reserveService.findConsumer(form.getConsumerEmail())
                .orElseThrow(() -> new CustomException(NOT_FOUND_CONSUMER));

        // 2. 예약하고자 하는 store이 등록된 store인지
        Store store = reserveService.findStore(form.getStoreName())
                .orElseThrow(() -> new CustomException(NOT_FOUND_STORE));

        // 3. 예약하고자 하는 시간이 현재보다 과거인지
        if (reserveService.isWantedReserveTimePast(form.getReserveDateAndTime()) ) {
            throw new CustomException(WANT_TIME_IS_PAST);
        }

        // 4. 원하는 시간에 이미 예약이 존재하는지
        if (reserveService.isReserveTimeExist(form.getReserveDateAndTime())) {
            throw new CustomException(RESERVATION_IS_ALREADY_EXIST);
        }

        return reserveService.saveReserve(form, user, store);
    }
}
