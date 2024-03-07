package org.smarthammer.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.application.SignInApplication;
import org.smarthammer.reservation.domain.SignInForm;
import org.smarthammer.reservation.service.consumer.ConsumerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/signin")
@RequiredArgsConstructor
public class SignInController {

    private final SignInApplication signInApplication;

    // 상점 이용자 로그인 컨트롤러
    @PostMapping("/consumer")
    public ResponseEntity<String> signInConsumer(@RequestBody SignInForm form) {
        return ResponseEntity.ok(signInApplication.consumerLogin(form));
    }

    // 상점 관리자 (manager) 로그인 컨트롤러
    @PostMapping("/manager")
    public ResponseEntity<String> signInManager(@RequestBody SignInForm form) {
        return ResponseEntity.ok(signInApplication.managerLogin(form));
    }
}
