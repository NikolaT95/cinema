package com.NikolaTabas94rn.cinema.model.api.reservation;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReservationSaveDto {
    int screening_id;

    int user_reserved_id;

    Boolean paid;

    Boolean active;
}
