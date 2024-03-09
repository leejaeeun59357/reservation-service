package org.smarthammer.reservation.controller.Reserve;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.application.ManagerOnlyApplication;
import org.smarthammer.reservation.domain.dto.ReserveDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerOnlyController {

    private final ManagerOnlyApplication managerOnlyApplication;

    /**
     * managerEmail을 통해 해당 상점 주인을 찾고
     * 예약자 이름과 예약 시간을 통해 해당 예약을 찾는다
     *
     * 예약자가 예약한 상점과 상점주인이 일치하는지 확인 후
     * 승인 실시
     *
     * @param managerEmail
     * @param consumerName
     * @param reserveTime
     * @return
     */
    @PutMapping("/approve")
    public ResponseEntity<ReserveDto> approveReservation(
            @RequestParam(name = "managerEmail") String managerEmail,

            @RequestParam(name = "consumerName") String consumerName,

            @RequestParam(name = "reserveTime")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
            LocalDateTime reserveTime
    ) {
        return ResponseEntity.ok(managerOnlyApplication.allowReservation(
                managerEmail, consumerName, reserveTime)
        );
    }

}
