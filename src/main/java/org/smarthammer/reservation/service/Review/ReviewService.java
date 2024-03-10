package org.smarthammer.reservation.service.Review;

import lombok.RequiredArgsConstructor;
import org.smarthammer.reservation.domain.Form.user.AddReviewForm;
import org.smarthammer.reservation.domain.dto.ReviewDto;
import org.smarthammer.reservation.domain.model.Consumer;
import org.smarthammer.reservation.domain.model.Reserve;
import org.smarthammer.reservation.domain.model.Review;
import org.smarthammer.reservation.domain.repository.ConsumerRepository;
import org.smarthammer.reservation.domain.repository.ReserveRepository;
import org.smarthammer.reservation.domain.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReserveRepository reserveRepository;
    private final ConsumerRepository consumerRepository;
    private final ReviewRepository reviewRepository;

    /**
     * 예약한 이름과 예약한 시간을 통해 해당 예약이 존재하는지
     *
     * @param form
     * @return Reserve Entity
     */
    public Optional<Reserve> findReservation(AddReviewForm form) {
        Optional<Reserve> reserve = reserveRepository.findByNameAndDT(form.getReserveName(),form.getReserveTime());

        return reserve;
    }

    /**
     * email을 이용하여 해당 consumer이 존재하는지 확인
     * @param consumerEmail
     * @return Consumer Entity
     */
    public Optional<Consumer> isConsumerExist(String consumerEmail) {
        Optional<Consumer> consumer = consumerRepository.findByEmail(consumerEmail);
        return consumer;
    }

    /**
     * reserve를 통해 review가 등록되기 전 total_star값을 가져온다
     * review에서 입력한 star를 store 의 total_star에 더해서 다시 저장하고
     * review를 저장
     *
     * @param form
     * @param reserve
     * @return
     */
    public ReviewDto saveReview(AddReviewForm form, Reserve reserve) {
        Long storeOriginalStar = reserve.getStore().getTotal_star();

        Review review = ReviewDto.formToEntity(form);
        review.setReserve(reserve);
        review.getReserve().getStore().setTotal_star(storeOriginalStar + review.getStar());

        return ReviewDto.entityToDto(reviewRepository.save(review));
    }


    /**
     * 해당 예약이 이미 리뷰가 작성되어있는지 검사
     * @param reserve
     * @return
     */
    public boolean isReviewExist(Reserve reserve) {
        return reviewRepository.findByReserve(reserve).isPresent();
    }
}
