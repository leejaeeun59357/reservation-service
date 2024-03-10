package org.smarthammer.reservation.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smarthammer.reservation.Exception.CustomException;
import org.smarthammer.reservation.Exception.ErrorCode;
import org.smarthammer.reservation.domain.Status.ArriveStatus;
import org.smarthammer.reservation.domain.Status.UseStatus;
import org.smarthammer.reservation.domain.dto.ReserveDto;
import org.smarthammer.reservation.domain.model.Reserve;
import org.smarthammer.reservation.service.Reserve.KioskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class KioskApplication {

    private final KioskService kioskService;

    /**
     * 예약내역이 존재하는지 검사하고,
     * 예약시간보다 10분전에 도착했는지 검사
     *
     * @param name
     * @param reserveTime
     * @return ReserveDto
     */
    @Transactional
    public ReserveDto consumerArrived(String name, LocalDateTime reserveTime) {

        // 해당 예약 내역이 존재하는지 확인
        // 없다면 에러 발생
        Reserve reserve = kioskService.findReserveConsumer(name, reserveTime)
                .orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_HISTORY_NOT_EXIST));

        // 상점 주인이 승인한 예약인지 검사
        if(kioskService.isRefuseReservation(reserve)) {
            throw new CustomException(ErrorCode.CANNOT_USE_STORE);
        }


        // 예약시간 10분 전에 도착했는지 확인
        // 도착했다면 useStatus 도 사용으로 변경
        if (kioskService.isBeforeTenMin(reserve)) {
            reserve.setArriveStatus(ArriveStatus.ARRIVED);
            reserve.setUseStatus(UseStatus.USED);
        }

        ReserveDto reserveDto = ReserveDto.entityToDto(reserve);

        return reserveDto;
    }
}
