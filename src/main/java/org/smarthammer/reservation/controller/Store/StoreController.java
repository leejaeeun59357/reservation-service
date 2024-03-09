package org.smarthammer.reservation.controller.Store;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.application.StoreAddApplication;
import org.smarthammer.reservation.application.StoreUpdateApplication;
import org.smarthammer.reservation.domain.Form.Store.AddStoreForm;
import org.smarthammer.reservation.domain.Form.Store.UpdateStoreForm;
import org.smarthammer.reservation.domain.dto.StoreDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreAddApplication storeAddApplication;
    private final StoreUpdateApplication storeUpdateApplication;

    @PostMapping("/add")
    public ResponseEntity<StoreDto> addStore(@RequestBody AddStoreForm form) {
        return ResponseEntity.ok(storeAddApplication.addStore(form));
    }

    @PutMapping("/update")
    public ResponseEntity<StoreDto> updateStore(@RequestBody UpdateStoreForm form) {
        return ResponseEntity.ok(storeUpdateApplication.updateStore(form));
    }
}
