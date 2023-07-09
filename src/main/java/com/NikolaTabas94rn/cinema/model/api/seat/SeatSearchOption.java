package com.NikolaTabas94rn.cinema.model.api.seat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatSearchOption {
    Integer page;

    Integer pageSize;
}
