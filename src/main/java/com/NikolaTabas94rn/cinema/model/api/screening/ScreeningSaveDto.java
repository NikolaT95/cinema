package com.NikolaTabas94rn.cinema.model.api.screening;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;
@Value
@Builder
public class ScreeningSaveDto {
    @Schema(description = "Id of movie", example = "1")
    int movie_id;
    @Schema(description = "Id of auditorium", example = "1")
    int auditorium_id;
    @Schema(description = "Screening start time", example = "2023-06-29 12:34:56")
    Timestamp screeningStart;
}
