package org.smarthammer.reservation.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smarthammer.reservation.Exception.CustomException;
import org.smarthammer.reservation.domain.model.Review;
import org.smarthammer.reservation.service.Review.ReviewDeleteService;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.smarthammer.reservation.Exception.ErrorCode.DO_NOT_HAVE_RIGHT;
import static org.smarthammer.reservation.Exception.ErrorCode.NOT_EXIST_REVIEW;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewDeleteApplication {

    private final ReviewDeleteService reviewDeleteService;

    public String deleteReview(String inputEmail, String title) {
        // 해당 리뷰가 존재하는지
        Review review = reviewDeleteService.findReview(title)
                .orElseThrow(() -> new CustomException(NOT_EXIST_REVIEW));

        // 점장 또는 작성자인지
        String managerEmail =
                review.getReserve().getStore().getManager().getEmail();

        String reviewWriter =
                review.getReserve().getConsumer().getEmail();

        if (!Objects.equals(inputEmail, managerEmail) &&
                !Objects.equals(inputEmail, reviewWriter)) {
            throw new CustomException(DO_NOT_HAVE_RIGHT);
        }

        return reviewDeleteService.deleteReview(review);
    }
}
