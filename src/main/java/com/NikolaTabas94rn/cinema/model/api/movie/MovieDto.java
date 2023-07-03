package com.NikolaTabas94rn.cinema.model.api.movie;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MovieDto {

    int id;

    String  title;

    String director;

    String genre;

    String description;

    int duration_min;
}
