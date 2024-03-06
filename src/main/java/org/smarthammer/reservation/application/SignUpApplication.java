package org.smarthammer.reservation.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smarthammer.reservation.Exception.CustomException;
import org.smarthammer.reservation.Exception.ErrorCode;
import org.smarthammer.reservation.domain.SignUpForm;
import org.smarthammer.reservation.domain.model.Consumer;
import org.smarthammer.reservation.domain.model.Manager;
import org.smarthammer.reservation.service.consumer.SignUpConsumerService;
import org.smarthammer.reservation.service.manager.SignUpManagerService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignUpApplication {
    private final SignUpConsumerService signUpConsumerService;
    private final SignUpManagerService signUpManagerService;

    // consumer 회원가입 시 이미 등록된 이메일이라면 에러 발생
    public String consumerSignUp(SignUpForm form) {
        if (signUpConsumerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_USER);
        } else {
             Consumer c = signUpConsumerService.signUp(form);
            return "회원 가입에 성공하였습니다";
        }
    }

    // manager 회원 가입 시 이미 등록된 이메일이라면 에러 발생
    public String managerSignUp(SignUpForm form) {
        if (signUpManagerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_USER);
        } else {
            Manager m = signUpManagerService.signUp(form);
            return "회원 가입에 성공하였습니다";
        }
    }
}