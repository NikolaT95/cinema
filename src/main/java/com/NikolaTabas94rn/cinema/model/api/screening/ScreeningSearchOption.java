package com.NikolaTabas94rn.cinema.model.api.screening;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningSearchOption {
    Integer page;

    Integer pageSize;
}
