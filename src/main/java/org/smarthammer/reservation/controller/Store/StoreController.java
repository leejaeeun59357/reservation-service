package org.smarthammer.reservation.controller.Store;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.application.StoreAddApplication;
import org.smarthammer.reservation.domain.Form.Store.AddStoreForm;
import org.smarthammer.reservation.domain.dto.StoreDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreAddApplication storeAddApplication;

    @PostMapping("/add")
    public ResponseEntity<StoreDto> addStore(@RequestBody AddStoreForm form) {
        return ResponseEntity.ok(storeAddApplication.addStore(form));
    }
}
