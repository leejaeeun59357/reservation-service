package org.smarthammer.reservation.controller.Store;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.application.StoreAddApplication;
import org.smarthammer.reservation.application.StoreDeleteApplication;
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
    private final StoreDeleteApplication storeDeleteApplication;

    /**
     * 사용자에게서 addstoreForm을 받아
     * repository에 store를 저장하는 컨트롤러
     * @param addStoreForm
     * @return ResponseEntity<StoreDto>
     */
    @PostMapping("/add")
    public ResponseEntity<StoreDto> addStore(@RequestBody AddStoreForm addStoreForm) {
        return ResponseEntity.ok(storeAddApplication.addStore(addStoreForm));
    }


    /**
     * 사용자에게 updateStoreForm 을받아
     * store의 이름과 설명을 변경하는 컨트롤러
     * @param updateStoreForm
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<StoreDto> updateStore(@RequestBody UpdateStoreForm updateStoreForm) {
        return ResponseEntity.ok(storeUpdateApplication.updateStore(updateStoreForm));
    }

    /**
     * 삭제할 store 의 이름으로 삭제하기
     * @param name
     * @return "삭제가 완료되었습니다."
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStore(@RequestParam(value = "name") String name) {
        String s = storeDeleteApplication.deleteStore(name);

        return ResponseEntity.ok(s);
    }
}
