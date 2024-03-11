package org.smarthammer.reservation.controller.User;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.smarthammer.reservation.application.StoreListApplication;
import org.smarthammer.reservation.domain.Form.user.StoreListLookupForm;
import org.smarthammer.reservation.domain.dto.StoreListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/list")
public class StoreListController {

    private final StoreListApplication storeListApplication;

    @GetMapping("/all/store")
    public ResponseEntity<List<StoreListDto>> listStore(
            @RequestBody StoreListLookupForm storeListLookupForm
    ) {
        return ResponseEntity.ok(storeListApplication.findStoreList(storeListLookupForm));
    }

    @GetMapping("/store/selected")
    public ResponseEntity<StoreListDto> StoreDetail(@RequestParam(name = "storeName") String storeName) {
        return ResponseEntity.ok(storeListApplication.storeDetails(storeName));
    }
}
