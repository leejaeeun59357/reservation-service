package org.smarthammer.reservation.domain.dto;

import lombok.*;
import org.smarthammer.reservation.domain.Form.user.SignUpForm;
import org.smarthammer.reservation.domain.model.Consumer;

import java.util.Locale;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerDto {

    private Long consumer_id;
    private String email;
    private String name;
    private String phone;

    /**
     * controller를 통해 받은 form을 consumer entity형식으로 변환
     *
     * @param form
     * @return Consumer
     */
    public static Consumer formToEntity(SignUpForm form) {
        return Consumer.builder()
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .password(form.getPassword())
                .name(form.getName())
                .phone(form.getPhone())
                .build();
    }


    /**
     * Consumer Entity를 ConsumerDto 객체로 변환
     *
     * @param consumer
     * @return
     */
    public static ConsumerDto entityToDto(Consumer consumer) {
        return ConsumerDto.builder()
                .consumer_id(consumer.getConsumer_id())
                .email(consumer.getEmail())
                .name(consumer.getName())
                .phone(consumer.getPhone())
                .build();
    }

}
