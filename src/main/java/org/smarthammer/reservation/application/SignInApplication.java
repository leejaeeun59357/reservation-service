package org.smarthammer.reservation.application;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.Exception.CustomException;
import org.smarthammer.reservation.Exception.ErrorCode;
import org.smarthammer.reservation.domain.SignInForm;
import org.smarthammer.reservation.domain.model.Consumer;
import org.smarthammer.reservation.service.consumer.ConsumerService;
import org.smarthammer.reservation.service.manager.ManagerService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInApplication {
    private final ConsumerService consumerService;
    private final ManagerService managerService;


    public String consumerLogin(SignInForm form) {
        Consumer c = consumerService.findByEmailAndPassword(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        return "로그인 성공입니다.";
    }
}