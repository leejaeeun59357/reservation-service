package org.smarthammer.reservation.domain.dto;

import lombok.*;
import org.smarthammer.reservation.domain.Form.Reserve.AddReserveForm;
import org.smarthammer.reservation.domain.Status.AllowStatus;
import org.smarthammer.reservation.domain.Status.ArriveStatus;
import org.smarthammer.reservation.domain.Status.UseStatus;
import org.smarthammer.reservation.domain.model.Reserve;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReserveDto {

    private Long id;
    private String name;

    private LocalDateTime reserveDateAndTime;

    private AllowStatus allowStatus;
    private UseStatus useStatus;
    private ArriveStatus arriveStatus;

    private UserDto userDto;

    private StoreDto storeDto;


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
                .allowStatus(reserve.getAllowStatus())
                .arriveStatus(reserve.getArriveStatus())
                .userDto(UserDto.entityToDto(reserve.getConsumer()))
                .storeDto(StoreDto.entityToDto(reserve.getStore()))
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
                .allowStatus(AllowStatus.WAITING_FOR_APPROVAL)
                .useStatus(UseStatus.NOT_USE)
                .arriveStatus(ArriveStatus.NOT_ARRIVE)
                .build();
    }
}
