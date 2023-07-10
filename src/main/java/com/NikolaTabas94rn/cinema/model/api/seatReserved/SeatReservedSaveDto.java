package com.NikolaTabas94rn.cinema.model.api.seatReserved;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
@Schema(name = "SeatReservedSave")
public class SeatReservedSaveDto {
    @Schema(description = "Id of reservation", example = "1")
    @NotBlank
    int reservation_id;
    @Schema(description = "Id of seat", example = "1")
    @NotBlank
    int seat_id;
    @Schema(description = "Id of screening", example = "1")
    @NotBlank
    int screening_id;
}
