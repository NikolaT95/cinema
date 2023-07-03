package com.NikolaTabas94rn.cinema.model.api.reservation;

import com.NikolaTabas94rn.cinema.model.entity.ScreeningEntity;
import com.NikolaTabas94rn.cinema.model.entity.UserEntity;
import lombok.Builder;
import lombok.Value;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Value
@Builder
public class ReservationDto {
    int id;

    int screening_id;

    int user_reserved_id;

    Boolean paid;

    Boolean active;
}
