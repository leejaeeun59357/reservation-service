package org.smarthammer.reservation.controller.User;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.application.SignUpApplication;
import org.smarthammer.reservation.domain.Form.user.SignUpForm;
import org.smarthammer.reservation.domain.dto.UserDto;
import org.smarthammer.reservation.domain.model.Type;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpApplication signUpApplication;

    /**
     * 상점 이용자 회원가입
     *
     * @param form
     * @return ConsumerDto
     */
    @PostMapping("/consumer")
    public ResponseEntity<UserDto> consumerSignUp(@RequestBody SignUpForm form) {
        return ResponseEntity.ok(signUpApplication.signUp(form,
            Type.CONSUMER));
    }


    /**
     * 상점 이용자 회원가입
     * @param form
     * @return ManagerDto
     */
    @PostMapping("/manager")
    public ResponseEntity<UserDto> managerSignUp(@RequestBody SignUpForm form) {
        return ResponseEntity.ok(signUpApplication.signUp(form, Type.MANAGER));
    }

}