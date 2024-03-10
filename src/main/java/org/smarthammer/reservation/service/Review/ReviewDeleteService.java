package org.smarthammer.reservation.service.Review;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.domain.model.Review;
import org.smarthammer.reservation.domain.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewDeleteService {
    private final ReviewRepository reviewRepository;

    /**
     * title을 이용하여 해당 리뷰 찾기
     *
     * @param title
     * @return
     */
    public Optional<Review> findReview(String title) {
        return reviewRepository.findByTitle(title);
    }

    public String deleteReview(Review review) {
        reviewRepository.delete(review);

        return "삭제를 완료하였습니다.";
    }
}