package org.smarthammer.reservation.service.manager;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.domain.Form.Store.AddStoreForm;
import org.smarthammer.reservation.domain.dto.StoreDto;
import org.smarthammer.reservation.domain.model.Manager;
import org.smarthammer.reservation.domain.model.Store;
import org.smarthammer.reservation.domain.repository.ManagerRepository;
import org.smarthammer.reservation.domain.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final ManagerRepository managerRepository;

    /**
     * repository에 store에 대한 데이터 저장
     * @param form
     * @return StoreDto
     */
    public StoreDto saveStore(AddStoreForm form, Manager manager) {
        Store store = StoreDto.formToEntity(form);
        store.setManager(manager);

        return StoreDto.entityToDto(storeRepository.save(store));
    }

    /**
     * form 을 통해 입력받은 managerId에 따른 manager 객체가 존재하는지 검사
     * @param form
     * @return Manager
     */
    public Optional<Manager> findManager(AddStoreForm form) {
        Optional<Manager> findManagerValue = managerRepository.findById(form.getManagerId());

        return findManagerValue;
    }

    /**
     * 동일한 매장명이 존재하는지 확인
     * @param name
     * @return boolean
     */
    public boolean isNameExist(String name) {
        return storeRepository.findByName(name).isPresent();
    }

    /**
     * store 이름으로 store 객체 찾기
     *
     * @param name
     * @return Store
     */
    public Optional<Store> findStore(String name) {
        Optional<Store> store = storeRepository.findByName(name);

        return store;
    }
}
