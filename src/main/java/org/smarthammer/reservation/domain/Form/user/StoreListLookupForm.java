package org.smarthammer.reservation.domain.Form.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreListLookupForm {
    // x 좌표 (위도)
    private double lat;

    // y 좌표 (경도)
    private double lng;
}