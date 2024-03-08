package org.smarthammer.reservation.domain.Form.Store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddStoreForm {

    private Long managerId;

    // 식당인지 점포인지 구분하기 위한 변수
    private String store_identifier;
    private String name;
    private String description;

    // x 좌표 (위도)
    private String lat;

    // y 좌표 (경도)
    private String lng;
}
