package com.NikolaTabas94rn.cinema.model.api.seat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@Builder
@Schema(name = "SeatSave")
public class SeatSaveDto {
    @Schema(description = "Seat row number", example = "1")
    @NotNull
    int row;
    @Schema(description = "Seat number", example = "1")
    @NotNull
    int number;
    @Schema(description = "Id of auditorium", example = "1")
    @NotNull
    int auditorium_id;
}
