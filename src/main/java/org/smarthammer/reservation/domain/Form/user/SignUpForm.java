package org.smarthammer.reservation.domain.Form.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {
    private String email;
    private String password;
    private String name;
    private String phone;
}
