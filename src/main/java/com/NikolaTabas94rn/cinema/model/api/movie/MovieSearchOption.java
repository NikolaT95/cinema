package com.NikolaTabas94rn.cinema.model.api.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieSearchOption {
    Integer page;

    Integer pageSize;

    SortByField sortBy;

    Sort.Direction sortDirection;

    String genre;

    String title;

    public enum SortByField {
        director,
        title,
        duration,
    }
}
