package org.smarthammer.reservation.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smarthammer.reservation.Exception.CustomException;
import org.smarthammer.reservation.Exception.ErrorCode;
import org.smarthammer.reservation.domain.Status.AllowStatus;
import org.smarthammer.reservation.domain.dto.ReserveDto;
import org.smarthammer.reservation.domain.model.Reserve;
import org.smarthammer.reservation.domain.model.User;
import org.smarthammer.reservation.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.smarthammer.reservation.Exception.ErrorCode.NOT_FOUND_MANAGER;
import static org.smarthammer.reservation.Exception.ErrorCode.NOT_YOUR_STORE;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManagerOnlyApplication {
    private final UserService userService;

    /**
     * 1. 해당 상점 관리자 (manager) 이 존재하는지
     * 2. 이름과 예약시간을 이용하여 해당 예약 내역이 존재하는지
     * 3. 본인의 가게만 승인 가능하도록
     * 검사한 후 승인이 가능하도록 함
     *
     * @param managerEmail
     * @param name
     * @param reserveTime
     * @return
     */
    @Transactional
    public ReserveDto allowReservation(String managerEmail, String name, LocalDateTime reserveTime) {
        // 해당 상점 관리자 (manager) 이 존재하는지
        User user = userService.findUser(managerEmail)
                .orElseThrow(() -> new CustomException(NOT_FOUND_MANAGER));


        // 이름과 예약시간을 이용하여 해당 예약 내역 조회
        Reserve reserve = userService.findReservation(name, reserveTime)
                .orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_HISTORY_NOT_EXIST));



        // 입력받은 manager 과 예약자가 예약한 상점주인이 동일인인지 검사
        // 본인의 가게만 승인 가능하도록
        if(reserve.getStore().getManager().getEmail() != user.getEmail()) {
            throw new CustomException(NOT_YOUR_STORE);
        }

        reserve.setAllowStatus(AllowStatus.APPROVAL);
        ReserveDto reserveDto = ReserveDto.entityToDto(reserve);

        return reserveDto;
    }


    /**
     * 1. 해당 상점 관리자 (manager) 이 존재하는지
     * 2. 이름과 예약시간을 이용하여 해당 예약 내역이 존재하는지
     * 3. 본인의 가게만 거절 가능하도록
     * 검사한 후 거절이 가능하도록 함
     *
     * @param managerEmail
     * @param name
     * @param reserveTime
     * @return
     */
    @Transactional
    public ReserveDto refuseReservation(String managerEmail, String name, LocalDateTime reserveTime) {
        // 해당 상점 관리자 (manager) 이 존재하는지
        User user = userService.findUser(managerEmail)
                .orElseThrow(() -> new CustomException(NOT_FOUND_MANAGER));


        // 이름과 예약시간을 이용하여 해당 예약 내역 조회
        Reserve reserve = userService.findReservation(name, reserveTime)
                .orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_HISTORY_NOT_EXIST));



        // 입력받은 manager 과 예약자가 예약한 상점주인이 동일인인지 검사
        // 본인의 가게만 승인 가능하도록
        if(reserve.getStore().getManager().getEmail() != user.getEmail()) {
            throw new CustomException(NOT_YOUR_STORE);
        }

        reserve.setAllowStatus(AllowStatus.REFUSED);
        ReserveDto reserveDto = ReserveDto.entityToDto(reserve);

        return reserveDto;
    }
}
