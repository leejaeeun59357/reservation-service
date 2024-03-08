package org.smarthammer.reservation.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smarthammer.reservation.Exception.CustomException;
import org.smarthammer.reservation.Exception.ErrorCode;
import org.smarthammer.reservation.domain.Form.user.SignUpForm;
import org.smarthammer.reservation.domain.dto.ConsumerDto;
import org.smarthammer.reservation.domain.dto.ManagerDto;
import org.smarthammer.reservation.domain.model.Consumer;
import org.smarthammer.reservation.domain.model.Manager;
import org.smarthammer.reservation.service.consumer.ConsumerService;
import org.smarthammer.reservation.service.manager.ManagerService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignUpApplication {
    private final ConsumerService consumerService;
    private final ManagerService managerService;

    /**
     * consumer 회원가입 시 이미 등록된 이메일이라면 에러 발생
     *
     * @param form
     * @return
     */
    public ConsumerDto consumerSignUp(SignUpForm form) {
        // 이미 회원가입 된 이메일인지 조회
        if (consumerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_USER);
        } else {
            Consumer consumer = consumerService.signUp(form);
            ConsumerDto consumerDto = ConsumerDto.entityToDto(consumer);

            return consumerDto;
        }
    }

    /**
     * manager 회원 가입 시 이미 등록된 이메일이라면 에러 발생
     *
     * @param form
     * @return ManagerDto
     */
    public ManagerDto managerSignUp(SignUpForm form) {
        if (managerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_USER);
        } else {
            Manager manager = managerService.signUp(form);
            ManagerDto managerDto = ManagerDto.entityToDto(manager);

            return managerDto;
        }
    }
}