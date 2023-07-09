package com.NikolaTabas94rn.cinema.model.api.reservation;

import com.NikolaTabas94rn.cinema.model.api.seatReserved.SeatReservedDto;
import com.NikolaTabas94rn.cinema.model.entity.SeatReservedEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ReservationSaveDto {
    /* int screening_id;

    int user_reserved_id;
*/
    @Schema(description = "Reservation is paid", example = "true")
    Boolean paid;
    @Schema(description = "Reservation is active", example = "true")
    Boolean active;
    @Schema(description = "Reserved seats", example = "[1,2,3]")
    List<SeatReservedDto> reservedSeats;
}
