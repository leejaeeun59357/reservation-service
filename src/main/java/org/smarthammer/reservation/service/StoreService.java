package org.smarthammer.reservation.service;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.domain.Form.Store.AddStoreForm;
import org.smarthammer.reservation.domain.dto.StoreDto;
import org.smarthammer.reservation.domain.model.Store;
import org.smarthammer.reservation.domain.model.User;
import org.smarthammer.reservation.domain.repository.StoreRepository;
import org.smarthammer.reservation.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    /**
     * repository에 store에 대한 데이터 저장
     * @param form
     * @return StoreDto
     */
    public StoreDto saveStore(AddStoreForm form, User manager) {
        Store store = StoreDto.formToEntity(form);
        store.setManager(manager);

        return StoreDto.entityToDto(storeRepository.save(store));
    }

    /**
     * form 을 통해 입력받은 managerId에 따른 manager 객체가 존재하는지 검사
     * @param form
     * @return Manager
     */
    public Optional<User> findManager(AddStoreForm form) {
        Optional<User> findManagerValue = userRepository.findByEmail(form.getManagerEmail());

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
     * @param name
     * @return Store
     */
    public Optional<Store> findStore(String name) {
        Optional<Store> store = storeRepository.findByName(name);
        return store;
    }

    /**
     * store 이름을 이용하여 삭제하기
     * @param store
     * @return "삭제가 완료되었습니다."
     */
    public String deleteStore(Store store) {
        storeRepository.delete(store);

        return "삭제가 완료되었습니다.";
    }


    /**
     * 모든 Store 의 정보 구하기
     * @return List<Store>
     */
    public List<Store> allStoreList() {
        return storeRepository.findAll();
    }
}