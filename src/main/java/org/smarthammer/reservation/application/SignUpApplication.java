package org.smarthammer.reservation.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smarthammer.reservation.Exception.CustomException;
import org.smarthammer.reservation.Exception.ErrorCode;
import org.smarthammer.reservation.domain.Form.user.SignUpForm;
import org.smarthammer.reservation.domain.dto.UserDto;
import org.smarthammer.reservation.domain.model.Type;
import org.smarthammer.reservation.domain.model.User;
import org.smarthammer.reservation.service.UserService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignUpApplication {
    private final UserService userService;

    /**
     * consumer 회원가입 시 이미 등록된 이메일이라면 에러 발생
     *
     * @param form
     * @return
     */
    public UserDto signUp(SignUpForm form, Type type) {
        // 이미 회원가입 된 이메일인지 조회
        if (userService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_USER);
        } else {
            User user = userService.signUp(form, type);
            UserDto userDto = UserDto.entityToDto(user);

            return userDto;
        }
    }

}