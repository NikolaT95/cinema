package com.NikolaTabas94rn.cinema.model.api.seat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Schema(name = "SeatSave")
public class SeatSaveDto {
    @Schema(description = "Seat row number", example = "1")
    int row;
    @Schema(description = "Seat number", example = "1")
    int number;
    @Schema(description = "Id of auditorium", example = "1")
    int auditorium_id;
}
