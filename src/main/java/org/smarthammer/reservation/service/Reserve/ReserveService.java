package org.smarthammer.reservation.service.Reserve;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.domain.Form.Reserve.AddReserveForm;
import org.smarthammer.reservation.domain.dto.ReserveDto;
import org.smarthammer.reservation.domain.model.Reserve;
import org.smarthammer.reservation.domain.model.Store;
import org.smarthammer.reservation.domain.model.User;
import org.smarthammer.reservation.domain.repository.ReserveRepository;
import org.smarthammer.reservation.domain.repository.StoreRepository;
import org.smarthammer.reservation.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReserveService {
    private final UserRepository userRepository;
    private final ReserveRepository reserveRepository;
    private final StoreRepository storeRepository;

    /**
     * 주어진 email을 통해 일치하는 consumer 가 존재하는지 확인
     *
     * @param email
     * @return Consumer Entity
     */
    public Optional<User> findConsumer(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        return user;
    }

    public Optional<Store> findStore(String name) {
        Optional<Store> store = storeRepository.findByName(name);

        return store;
    }

    /**
     * form을 통해 생성된 Reserve 객체에 주어진 consumer Entity를 매핑하여 repository에 저장
     *
     * @param form
     * @param user
     * @return
     */
    public ReserveDto saveReserve(AddReserveForm form, User user, Store store) {
        Reserve reserve = ReserveDto.formToEntity(form);
        reserve.setConsumer(user);
        reserve.setStore(store);

        return ReserveDto.entityToDto(reserveRepository.save(reserve));
    }


    /**
     * 예약을 원하는 시간에 이미 예약이 존재하는지 확인하는 메서드
     * @param wantToReserveTime
     * @return
     */
    public boolean isReserveTimeExist(LocalDateTime wantToReserveTime) {
        return reserveRepository.findByDT(wantToReserveTime).isPresent();
    }

    /**
     * 예약을 원하는 시간이 현재 시간보다 과거인지 검사하는 메서드
     * 과거라면 true
     * 아니라면 false 반환
     *
     * @param inputData
     * @return boolean
     */
    public boolean isWantedReserveTimePast(LocalDateTime inputData) {
        return inputData.isBefore(LocalDateTime.now());
    }
}
