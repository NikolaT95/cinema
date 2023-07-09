package com.NikolaTabas94rn.cinema.model.api.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationSearchOption {
    Integer page;

    Integer pageSize;
}
