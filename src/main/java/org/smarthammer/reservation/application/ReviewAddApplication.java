package org.smarthammer.reservation.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smarthammer.reservation.Exception.CustomException;
import org.smarthammer.reservation.Exception.ErrorCode;
import org.smarthammer.reservation.domain.Form.user.AddReviewForm;
import org.smarthammer.reservation.domain.Status.UseStatus;
import org.smarthammer.reservation.domain.dto.ReviewDto;
import org.smarthammer.reservation.domain.model.Reserve;
import org.smarthammer.reservation.domain.model.User;
import org.smarthammer.reservation.service.Review.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewAddApplication {

    private final ReviewService reviewService;


    /**
     * 리뷰추가하는 form을 입력받아 저장
     *
     * @param form
     * @return ReviewDto
     */
    @Transactional
    public ReviewDto addReview(AddReviewForm form) {

        // 해당 예약 정보가 존재하는지 검사
        Reserve reserve = reviewService.findReservation(form)
                .orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_HISTORY_NOT_EXIST));

        // 리뷰를 작성하고자 하는 Consumer이 존재하는지 검사
        User consumer = reviewService.isConsumerExist(form.getConsumerEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        // 예약한 consumer과 리뷰를 작성하려는 consumer이 동일한지 검사
        if(reserve.getConsumer() != consumer) {
            throw new CustomException(ErrorCode.DIFFERENT_CONSUMER);
        }

        // 예약자가 예약 상점을 사용했는지 검사
        if(reserve.getUseStatus() != UseStatus.USED) {
            throw new CustomException(ErrorCode.NOT_USE_CONSUMER);
        }

        // 해당 예약에 따른 리뷰를 이미 작성했는지 검사
        if(reviewService.isReviewExist(reserve)) {
            throw new CustomException(ErrorCode.ALREADY_WRITE);
        }

        // form을 통해 받은 별점을 store에 업데이트 시키기
        return reviewService.saveReview(form, reserve);
    }
}