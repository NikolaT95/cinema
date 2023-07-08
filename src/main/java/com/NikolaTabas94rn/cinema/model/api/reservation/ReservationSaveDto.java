package com.NikolaTabas94rn.cinema.model.api.reservation;

import com.NikolaTabas94rn.cinema.model.api.seatReserved.SeatReservedDto;
import com.NikolaTabas94rn.cinema.model.entity.SeatReservedEntity;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ReservationSaveDto {
    /* int screening_id;

    int user_reserved_id;
*/
    Boolean paid;

    Boolean active;

    List<SeatReservedDto> reservedSeats;
}
