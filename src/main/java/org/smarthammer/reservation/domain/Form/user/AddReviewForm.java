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
public class AddReviewForm {

    private String consumerEmail;

    private String reserveName;

    // 형식 예시 -> "2010-11-25 12:30"
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime reserveTime;

    private Long star;
    private String feedback;
}