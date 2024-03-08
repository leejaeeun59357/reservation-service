package org.smarthammer.reservation.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smarthammer.reservation.Exception.CustomException;
import org.smarthammer.reservation.Exception.ErrorCode;
import org.smarthammer.reservation.domain.Form.Store.AddStoreForm;
import org.smarthammer.reservation.domain.dto.StoreDto;
import org.smarthammer.reservation.domain.model.Manager;
import org.smarthammer.reservation.domain.model.Store;
import org.smarthammer.reservation.service.manager.StoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreAddApplication {

    private final StoreService storeService;

    /**
     * store를 등록하는 transaction
     *
     * @param form
     * @return StoreDto
     */
    @Transactional
    public StoreDto addStore(AddStoreForm form) {
        Manager manager = storeService.findManager(form)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MANAGER));

        if(storeService.isNameExist(form.getName())) {
            throw new CustomException(ErrorCode.SAME_STORE_NAME);
        }

        return storeService.saveStore(form, manager);
    }
}
