package com.NikolaTabas94rn.cinema.model.api.seat;

import com.NikolaTabas94rn.cinema.model.entity.AuditoriumEntity;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SeatDto {
    int id;

    int row;

    int number;

    int auditorium_id;
}
