package com.NikolaTabas94rn.cinema.model.api.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieSearchOption {
    Integer page;

    Integer pageSize;
}
