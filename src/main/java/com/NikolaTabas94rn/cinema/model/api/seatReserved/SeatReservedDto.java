package com.NikolaTabas94rn.cinema.model.api.seatReserved;

import com.NikolaTabas94rn.cinema.model.entity.ReservationEntity;
import com.NikolaTabas94rn.cinema.model.entity.ScreeningEntity;
import com.NikolaTabas94rn.cinema.model.entity.SeatEntity;
import lombok.Builder;
import lombok.Value;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Value
@Builder
public class SeatReservedDto {
    int id;

    int reservation_id;

    int seat_id;

    int screening_id;
}
