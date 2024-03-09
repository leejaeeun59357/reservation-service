package org.smarthammer.reservation.controller.Reserve;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.application.ReserveAddApplication;
import org.smarthammer.reservation.domain.Form.Reserve.AddReserveForm;
import org.smarthammer.reservation.domain.dto.ReserveDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reserve")
public class ReserveController {

    private final ReserveAddApplication reserveAddApplication;

    @PostMapping("/add")
    public ResponseEntity<ReserveDto> addReserve(@RequestBody AddReserveForm form) {
        return ResponseEntity.ok(reserveAddApplication.addReserve(form));
    }
}