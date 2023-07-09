package com.NikolaTabas94rn.cinema.model.api.seatReserved;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatReservedSearchOption {
    Integer page;

    Integer pageSize;
}
