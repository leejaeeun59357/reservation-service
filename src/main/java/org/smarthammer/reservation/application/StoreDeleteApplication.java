package org.smarthammer.reservation.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smarthammer.reservation.Exception.CustomException;
import org.smarthammer.reservation.Exception.ErrorCode;
import org.smarthammer.reservation.domain.model.Store;
import org.smarthammer.reservation.service.StoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreDeleteApplication {

    private final StoreService storeService;

    /**
     * store 이름을 이용하여 삭제하기
     * @param name
     * @return "삭제가 완료되었습니다."
     */
    @Transactional
    public String deleteStore(String name) {
        // storeRepository에 이름을 이용하여 조회했을때
        // 존재하지 않는다면 에러 발생
        Store store = storeService.findStore(name)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_STORE));

        return storeService.deleteStore(store);
    }
}