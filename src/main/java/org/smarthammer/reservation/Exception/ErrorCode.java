package org.smarthammer.reservation.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 회원가입 관련
    ALREADY_REGISTERED_USER(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다."),

    // 로그인 관련
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "정보와 일치하는 회원을 찾을 수 없습니다."),

    // Store 관련
    NOT_FOUND_MANAGER(HttpStatus.BAD_REQUEST, "해당 상점주인을 찾을 수 없습니다. 상점관리자 회원가입을 해주세요."),
    SAME_STORE_NAME(HttpStatus.BAD_REQUEST, "동일한 매장명이 등록되어 있습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
