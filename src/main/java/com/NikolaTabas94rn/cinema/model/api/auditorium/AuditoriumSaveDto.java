package com.NikolaTabas94rn.cinema.model.api.auditorium;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Value
@Builder
@Schema(name = "AuditoriumSave")
public class AuditoriumSaveDto {
    @Schema(description = "Auditorium title", example = "Big auditorium")
    @NotBlank
    String name;
    @Schema(description = "number of seats in auditorium", example = "20")
    @NotEmpty
    int seats_no;
}
