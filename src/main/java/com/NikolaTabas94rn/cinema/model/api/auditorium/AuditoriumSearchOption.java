package com.NikolaTabas94rn.cinema.model.api.auditorium;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriumSearchOption {
    Integer page;

    Integer pageSize;
}
