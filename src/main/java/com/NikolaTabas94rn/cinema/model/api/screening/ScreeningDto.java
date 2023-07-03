package com.NikolaTabas94rn.cinema.model.api.screening;

import com.NikolaTabas94rn.cinema.model.entity.AuditoriumEntity;
import com.NikolaTabas94rn.cinema.model.entity.MovieEntity;
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

    Timestamp screening_start;
}
