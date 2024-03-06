package org.smarthammer.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.application.SignUpApplication;
import org.smarthammer.reservation.domain.SignUpForm;
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


    // 상점 이용자 회원가입하는 컨트롤러
    @PostMapping("/customer")
    public ResponseEntity<String> consumerSignUp(@RequestBody SignUpForm form) {
        return ResponseEntity.ok(signUpApplication.consumerSignUp(form));
    }

    // 상점 이용자 로그인하는 컨트롤러

    // 상점 관리자 회원가입하는 컨트롤러

    // 상점 관리자 로그인하는 컨트롤러

}