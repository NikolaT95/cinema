package com.NikolaTabas94rn.cinema.model.api.screening;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;
@Value
@Builder
public class ScreeningSaveDto {

    int movie_id;

    int auditorium_id;

    Timestamp screeningStart;
}
