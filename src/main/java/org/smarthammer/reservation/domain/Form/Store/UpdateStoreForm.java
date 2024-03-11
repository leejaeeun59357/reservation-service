package org.smarthammer.reservation.domain.Form.Store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStoreForm {

    // 변경되기 전 상점 이름
    private String beforeUpdateName;

    // 변경 희망하는 상점 이름
    private String afterUpdateName;

    private String description;
}
