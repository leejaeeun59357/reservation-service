package org.smarthammer.reservation.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smarthammer.reservation.Exception.CustomException;
import org.smarthammer.reservation.Exception.ErrorCode;
import org.smarthammer.reservation.domain.Form.Store.UpdateStoreForm;
import org.smarthammer.reservation.domain.dto.StoreDto;
import org.smarthammer.reservation.domain.model.Store;
import org.smarthammer.reservation.service.StoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreUpdateApplication {

    private final StoreService storeService;

    /**
     * UpdateForm 을 통해 받은 데이터로 store 를 업데이트 시키기
     * @param form
     * @return
     */
    @Transactional
    public StoreDto updateStore(UpdateStoreForm form) {
        // 만약 이미 등록된 이름로 변경하려면 에러 발생
        if(storeService.isNameExist(form.getAfterUpdateName())) {
            throw new CustomException(ErrorCode.SAME_STORE_NAME_IS_EXIST);
        }

        // store 찾기
        Store store = storeService.findStore(form.getBeforeUpdateName())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_STORE));

        // 업데이트 하기
        store.setName(form.getAfterUpdateName());
        store.setDescription(form.getDescription());

        StoreDto storeDto = StoreDto.entityToDto(store);

        return storeDto;
    }
}
