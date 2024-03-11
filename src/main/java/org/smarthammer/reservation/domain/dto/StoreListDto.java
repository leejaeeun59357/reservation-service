package org.smarthammer.reservation.domain.dto;

import lombok.*;
import org.smarthammer.reservation.domain.model.Store;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StoreListDto {

    private Long store_id;
    private double distance;
    private String store_identifier;
    private String name;
    private String description;
    private Long total_star;

    /**
     * Entity -> DTO
     * @param store Store Entity
     * @param distanceVal 두 지점 사이의 거리 값
     * @return
     */
    public static StoreListDto entityToListDto(
            Store store, double distanceVal
    ) {
        return StoreListDto.builder()
                .store_id(store.getStore_id())
                .distance(distanceVal)
                .store_identifier(store.getStore_identifier())
                .name(store.getName())
                .description(store.getDescription())
                .total_star(store.getTotal_star() )
                .build();
    }


    /**
     * 상점 엔티티를 StoreListDto 객체로 변환
     * @param store Store Entity
     * @return StoreListDto
     */
    public static StoreListDto entityToDto(Store store) {
        return StoreListDto.builder()
                .store_id(store.getStore_id())
                .store_identifier(store.getStore_identifier())
                .name(store.getName())
                .description(store.getDescription())
                .total_star(store.getTotal_star() )
                .build();
    }
}
