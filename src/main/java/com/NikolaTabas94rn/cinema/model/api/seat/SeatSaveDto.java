package com.NikolaTabas94rn.cinema.model.api.seat;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SeatSaveDto {
    int row;

    int number;

    int auditorium_id;
}
