package com.NikolaTabas94rn.cinema.model.api.auditorium;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Schema(name = "AuditoriumSave")
public class AuditoriumSaveDto {
    @Schema(description = "Auditorium title", example = "Big auditorium")
    String name;
    @Schema(description = "number of seats in auditorium", example = "20")
    int seats_no;
}
