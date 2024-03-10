package org.smarthammer.reservation.domain.Form.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReviewForm {

    private String consumerEmail;

    private String reviewTitle;

    private Long star;
    private String feedback;
}
