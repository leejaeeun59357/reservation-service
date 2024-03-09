package org.smarthammer.reservation.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.smarthammer.reservation.domain.Form.Reserve.AddReserveForm;
import org.smarthammer.reservation.domain.model.Reserve;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReserveDto {

    private Long id;
    private String name;

    private LocalDateTime reserveDateAndTime;

    private String useStatus;
    private String arriveStatus;

    private ConsumerDto consumerDto;


    /**
     * Reserve Entity를 ReserveDto 로 변환
     * @param reserve
     * @return ReserveDto
     */
    public static ReserveDto entityToDto(Reserve reserve) {
        return ReserveDto.builder()
                .id(reserve.getId())
                .name(reserve.getName())
                .reserveDateAndTime(reserve.getDT())
                .useStatus(reserve.getUseStatus())
                .arriveStatus(reserve.getArriveStatus())
                .consumerDto(ConsumerDto.entityToDto(reserve.getConsumer()))
                .build();
    }

    /**
     * AddReserveForm 을 통해 Reserve 객체로 변환하여 반환하는 메서드
     * @param form
     * @return Reserve  Entity
     */
    public static Reserve formToEntity(AddReserveForm form) {

        return Reserve.builder()
                .name(form.getName())
                .DT(
                        form.getReserveDateAndTime()
                )
                .useStatus("NOT_USE")
                .arriveStatus("NOT_ARRIVE")
                .build();
    }
}
