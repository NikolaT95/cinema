package com.NikolaTabas94rn.cinema.model.api.reservation;

import com.NikolaTabas94rn.cinema.model.entity.ScreeningEntity;
import com.NikolaTabas94rn.cinema.model.entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Value
@Builder
@Schema(name = "Reservation")
public class ReservationDto {
    @Schema(description = "Reservation identifier", example = "1")
    int id;
    @Schema(description = "Id for screening", example = "1")
    int screening_id;
    @Schema(description = "Id for user", example = "1")
    int user_reserved_id;
    @Schema(description = "Reservation is paid", example = "true")
    Boolean paid;
    @Schema(description = "Reservation is active", example = "true")
    Boolean active;
}
