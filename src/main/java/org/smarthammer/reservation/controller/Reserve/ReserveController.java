package org.smarthammer.reservation.controller.Reserve;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.application.ReserveAddApplication;
import org.smarthammer.reservation.domain.Form.Reserve.AddReserveForm;
import org.smarthammer.reservation.domain.dto.ReserveDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reserve")
public class ReserveController {

    private final ReserveAddApplication reserveAddApplication;

    /**
     * AddReserveForm 을 입력받아 예약을 저장
     *
     * @param addReserveForm
     * @return ReserveDto
     */
    @PostMapping("/add")
    public ResponseEntity<ReserveDto> addReserve(@RequestBody AddReserveForm addReserveForm) {
        return ResponseEntity.ok(reserveAddApplication.addReserve(addReserveForm));
    }

}