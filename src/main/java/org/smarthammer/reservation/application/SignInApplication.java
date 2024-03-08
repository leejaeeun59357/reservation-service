//package org.smarthammer.reservation.application;
//
//import lombok.RequiredArgsConstructor;
//import org.smarthammer.reservation.Exception.CustomException;
//import org.smarthammer.reservation.Exception.ErrorCode;
//import org.smarthammer.reservation.service.consumer.ConsumerService;
//import org.smarthammer.reservation.service.manager.ManagerService;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class SignInApplication {
//    private final ConsumerService consumerService;
//    private final ManagerService managerService;
//
//
//    // consumer 로그인 메서드
//    public String consumerLogin(SignInForm form) {
//        consumerService.findByEmailAndPassword(form.getEmail(), form.getPassword())
//                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
//
//        return "로그인 성공입니다.";
//    }
//
//    // manager 로그인 메서드
//    public String managerLogin(SignInForm form) {
//        managerService.findByEmailAndPassword(form.getEmail(), form.getPassword())
//                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
//
//        return "로그인 성공입니다.";
//    }
//}