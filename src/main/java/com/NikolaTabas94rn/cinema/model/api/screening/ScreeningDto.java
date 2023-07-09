package com.NikolaTabas94rn.cinema.model.api.screening;

import com.NikolaTabas94rn.cinema.model.entity.AuditoriumEntity;
import com.NikolaTabas94rn.cinema.model.entity.MovieEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Value
@Builder
@Schema(name = "Screening")
public class ScreeningDto {
    @Schema(description = "Screening identifier", example = "1")
    int id;
    @Schema(description = "Id of movie", example = "1")
    int movie_id;
    @Schema(description = "Id of auditorium", example = "1")
    int auditorium_id;
    @Schema(description = "Screening start time", example = "2023-06-29 12:34:56")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CET")
    Timestamp screeningStart;
}
