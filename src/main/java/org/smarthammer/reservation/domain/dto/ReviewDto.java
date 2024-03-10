package org.smarthammer.reservation.domain.dto;

import lombok.*;
import org.smarthammer.reservation.domain.Form.user.AddReviewForm;
import org.smarthammer.reservation.domain.model.Review;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long reviewId;
    private String consumerEmail;

    private String title;

    private Long star;
    private String feedback;

    /**
     * form을 entity로 변환
     *
     * @param form
     * @return Review Entity
     */
    public static Review formToEntity(AddReviewForm form) {
        return Review.builder()
                .title(form.getTitle())
                .star(form.getStar())
                .feedback(form.getFeedback())
                .build();
    }

    /**
     * entity를 dto로 변환
     * @param review
     * @return ReviewDto
     */
    public static ReviewDto entityToDto(Review review) {
        return ReviewDto.builder()
                .title(review.getTitle())
                .reviewId(review.getId())
                .consumerEmail(review.getReserve().getConsumer().getEmail())
                .star(review.getStar())
                .feedback(review.getFeedback())
                .build();
    }
}
