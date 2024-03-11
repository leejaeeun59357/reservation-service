package org.smarthammer.reservation.controller.User;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.application.ReviewAddApplication;
import org.smarthammer.reservation.application.ReviewDeleteApplication;
import org.smarthammer.reservation.application.ReviewUpdateApplication;
import org.smarthammer.reservation.domain.Form.user.AddReviewForm;
import org.smarthammer.reservation.domain.Form.user.UpdateReviewForm;
import org.smarthammer.reservation.domain.dto.ReviewDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewAddApplication reviewAddApplication;
    private final ReviewUpdateApplication reviewUpdateApplication;
    private final ReviewDeleteApplication reviewDeleteApplication;

    /**
     * form을 입력하여 review를 저장
     *
     * @param addReviewForm
     * @return ReviewDto
     */
    @PostMapping("/add")
    public ResponseEntity<ReviewDto> addReview(
            @RequestBody AddReviewForm addReviewForm
    ) {
        return ResponseEntity.ok(reviewAddApplication.addReview(addReviewForm));
    }

    /**
     * form을 입력하여 별점과 피드백 수정
     *
     * @param form
     * @return ReviewDto
     */
    @PutMapping("/update")
    public ResponseEntity<ReviewDto> updateReview(@RequestBody UpdateReviewForm form) {
        return ResponseEntity.ok(reviewUpdateApplication.updateReview(form));
    }


    /**
     * 해당 리뷰를 찾아서
     * 상점관리자 또는 리뷰 작성자만 삭제가능하도록 함
     *
     * @param ConsumerEmail
     * @param ReviewTitle
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteReview(
            @RequestParam(name = "ConsumerEmail") String ConsumerEmail,
            @RequestParam(name = "ReviewTitle") String ReviewTitle
    ) {
        return ResponseEntity.ok(reviewDeleteApplication.deleteReview(ConsumerEmail, ReviewTitle));
    }

}