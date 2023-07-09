package com.NikolaTabas94rn.cinema.model.api.auditorium;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Schema(name = "Auditorium")
public class AuditoriumDto {
    @Schema(description = "Auditorium identifier", example = "1")
    int id;
    @Schema(description = "Auditorium name", example = "Big auditorium")
    String name;
    @Schema(description = "number of seats in auditorium", example = "20")
    int seats_no;
}
