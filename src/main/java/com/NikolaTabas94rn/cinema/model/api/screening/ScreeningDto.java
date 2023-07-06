package com.NikolaTabas94rn.cinema.model.api.screening;

import com.NikolaTabas94rn.cinema.model.entity.AuditoriumEntity;
import com.NikolaTabas94rn.cinema.model.entity.MovieEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Value
@Builder
public class ScreeningDto {
    int id;

    int movie_id;

    int auditorium_id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CET")
    Timestamp screeningStart;
}
