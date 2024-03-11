package org.smarthammer.reservation.controller.User;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.application.StoreListApplication;
import org.smarthammer.reservation.domain.Form.user.StoreListLookupForm;
import org.smarthammer.reservation.domain.dto.StoreListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/list")
public class StoreListController {

    private final StoreListApplication storeListApplication;

    @GetMapping
    public ResponseEntity<List<StoreListDto>> listStore(
            @RequestBody StoreListLookupForm storeListLookupForm
    ) {
        return ResponseEntity.ok(storeListApplication.findStoreList(storeListLookupForm));
    }
}
