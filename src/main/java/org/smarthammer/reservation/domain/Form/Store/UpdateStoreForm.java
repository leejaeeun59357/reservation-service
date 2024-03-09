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

    private String beforeUpdateName;
    private String afterUpdateName;
    private String description;
}
