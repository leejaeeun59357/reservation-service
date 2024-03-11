package org.smarthammer.reservation.controller.Reserve;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.application.KioskApplication;
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
@RequestMapping("/kiosk")
public class KioskController {

    private final KioskApplication kioskApplication;

    /**
     * 이름과 예약시간으로 해당 예약을 찾아 도착인증
     * 도착하게 되면 이용상태도 이용으로 변경
     *
     * @param consumerName
     * @param reserveTime
     * @return ReserveDto
     */
    @PutMapping("/arrived")
    public ResponseEntity<ReserveDto> consumerArrived(
            @RequestParam(name = "consumerName") String consumerName,

            @RequestParam(name = "reserveTime")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
            LocalDateTime reserveTime
            ) {
        return ResponseEntity.ok(
                kioskApplication.consumerArrived(consumerName, reserveTime)
        );
    }
}