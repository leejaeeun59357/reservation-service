package org.smarthammer.reservation.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smarthammer.reservation.Exception.CustomException;
import org.smarthammer.reservation.Exception.ErrorCode;
import org.smarthammer.reservation.domain.Form.user.UpdateReviewForm;
import org.smarthammer.reservation.domain.dto.ReviewDto;
import org.smarthammer.reservation.domain.model.Review;
import org.smarthammer.reservation.service.Review.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewUpdateApplication {

    private final ReviewService reviewService;

    /**
     * form을 입력받아
     * 1. 해당 리뷰가 존재하고
     * 2. 수정하려는 사람과 작성자가 동일인물인지 검사한 후
     * 3. 별점을 수정하고
     * 4. 리뷰를 수정한다.
     *
     * @param form
     * @return ReviewDto
     */
    @Transactional
    public ReviewDto updateReview(UpdateReviewForm form) {
        // 해당 리뷰가 존재하는지 확인
        Review review = reviewService.findReview(form.getReviewTitle())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_REVIEW));

        // 수정하려는 사람과 작성자가 동일인인지 검사
        if(!Objects.equals(form.getConsumerEmail(), review.getReserve().getConsumer().getEmail())) {
            throw new CustomException(ErrorCode.DO_NOT_HAVE_RIGHT);
        }

        long originalStarVAl = review.getStar();
        long originalTotalStarVal = review.getReserve().getStore().getTotal_star();

        // 별점 수정
        review.setStar(originalTotalStarVal - originalStarVAl + form.getStar());
        review.setFeedback(form.getFeedback());

        return ReviewDto.entityToDto(review);
    }
}
