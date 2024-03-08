package org.smarthammer.reservation.domain.dto;

import lombok.*;
import org.smarthammer.reservation.domain.Form.user.SignUpForm;
import org.smarthammer.reservation.domain.model.Manager;

import java.util.Locale;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDto {

    private Long manager_id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private Boolean partnership;

    /**
     * Manager Entity -> ManagerDto 객체
     *
     * @param manager
     * @return
     */
    public static ManagerDto entityToDto(Manager manager) {

        return ManagerDto.builder()
                .manager_id(manager.getId())
                .email(manager.getEmail())
                .name(manager.getName())
                .password(manager.getPassword())
                .phone(manager.getPhone())
                .partnership(manager.getPartnership())
                .build();
    }

    /**
     * 회원가입 form을 받아 Manager Entity로 변환
     *
     * @param form
     * @return Manager
     */
    public static Manager formToEntity(SignUpForm form) {
        return Manager.builder()
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .password(form.getPassword())
                .name(form.getName())
                .phone(form.getPhone())
                .partnership(true)
                .build();
    }

}
