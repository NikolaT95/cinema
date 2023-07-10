package com.NikolaTabas94rn.cinema.model.api.screening;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningSearchOption {
    Integer page;

    Integer pageSize;

    String specificDate;
}
