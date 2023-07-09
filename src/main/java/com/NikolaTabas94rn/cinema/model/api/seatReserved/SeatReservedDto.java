package com.NikolaTabas94rn.cinema.model.api.seatReserved;

import com.NikolaTabas94rn.cinema.model.entity.ReservationEntity;
import com.NikolaTabas94rn.cinema.model.entity.ScreeningEntity;
import com.NikolaTabas94rn.cinema.model.entity.SeatEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Value
@Builder
@Schema(name = "SeatReserved")
public class SeatReservedDto {
    @Schema(description = "Reserved seat identifier", example = "1")
    int id;
    @Schema(description = "Id of reservation", example = "1")
    int reservation_id;
    @Schema(description = "Id of seat", example = "1")
    int seat_id;
    @Schema(description = "Id of screening", example = "1")
    int screening_id;
}
