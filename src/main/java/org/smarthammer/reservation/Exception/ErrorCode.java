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
    NOT_FOUND_MANAGER(HttpStatus.BAD_REQUEST, "해당 상점주인을 찾을 수 없습니다."),
    NOT_FOUND_STORE(HttpStatus.BAD_REQUEST, "해당 상점을 찾을 수 없습니다."),
    SAME_STORE_NAME_IS_EXIST(HttpStatus.BAD_REQUEST, "동일한 매장명이 등록되어 있습니다."),

    // Reserve 관련
    NOT_FOUND_CONSUMER(HttpStatus.BAD_REQUEST, "해당 이용자를 찾을 수 없습니다. Consumer 회원가입을 먼저 진행하세요."),
    WANT_TIME_IS_PAST(HttpStatus.BAD_REQUEST, "현재보다 과거인 시간은 예약할 수 없습니다."),
    RESERVATION_HISTORY_NOT_EXIST(HttpStatus.BAD_REQUEST, "예약 내역이 존재하지 않습니다."),
    NOT_YOUR_STORE(HttpStatus.BAD_REQUEST, "본인의 가게만 승인할 수 있습니다."),
    CANNOT_USE_STORE(HttpStatus.BAD_REQUEST, "주인에 의해 거절된 예약입니다."),
    WAITING_MANAGER_ALLOW(HttpStatus.BAD_REQUEST, "주인에 의해 거절된 예약입니다."),
    RESERVATION_IS_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "다른 사람이 이미 예약한 시간입니다."),

    // Review 관련
    NOT_USE_CONSUMER(HttpStatus.BAD_REQUEST, "해당 상점을 이용한 사람만 리뷰를 작성할 수 있습니다."),
    ALREADY_WRITE(HttpStatus.BAD_REQUEST, "이미 리뷰를 작성하였습니다."),
    DIFFERENT_CONSUMER(HttpStatus.BAD_REQUEST, "예약한 사람과 작성하고자 하는 사람이 다릅니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
