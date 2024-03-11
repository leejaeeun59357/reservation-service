package org.smarthammer.reservation.domain.dto;

import lombok.*;
import org.smarthammer.reservation.domain.Form.Store.AddStoreForm;
import org.smarthammer.reservation.domain.model.Manager;
import org.smarthammer.reservation.domain.model.Store;
import org.smarthammer.reservation.domain.repository.ManagerRepository;
import org.smarthammer.reservation.domain.repository.StoreRepository;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {

    private Long store_id;
    private String store_identifier;
    private String name;
    private String description;
    private Long total_star;

    private String lat;
    private String lng;

    private ManagerDto managerDto;

    /**
     * Store Entity -> StoreDto
     *
     * @param store
     * @return StoreDto
     */
    public static StoreDto entityToDto(Store store){
        return StoreDto.builder()
                .store_id(store.getStore_id())
                .store_identifier(store.getStore_identifier())
                .name(store.getName())
                .description(store.getDescription())
                .total_star(store.getTotal_star())
                .lat(store.getLat())
                .lng(store.getLng())
                .managerDto(ManagerDto.entityToDto(store.getManager()))
                .build();
    }


    /**
     * controller를 통해 받은 form을 repository에 저장하기 위해 entity타입으로 변환
     *
     * @param form
     * @return Store
     */
    public static Store formToEntity(AddStoreForm form) {
        return Store.builder()
                .store_identifier(form.getStore_identifier())
                .name(form.getName())
                .description(form.getDescription())
                .lat(form.getLat())
                .lng(form.getLng())
                .total_star(0L)
                .build();
    }

}
