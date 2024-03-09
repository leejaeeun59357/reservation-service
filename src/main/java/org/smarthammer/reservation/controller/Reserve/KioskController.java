package org.smarthammer.reservation.controller.Reserve;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.application.KioskApplication;
import org.smarthammer.reservation.domain.dto.ReserveDto;
import org.smarthammer.reservation.service.Reserve.KioskService;
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