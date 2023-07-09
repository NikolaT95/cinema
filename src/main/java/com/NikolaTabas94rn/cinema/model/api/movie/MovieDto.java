package com.NikolaTabas94rn.cinema.model.api.movie;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Schema(name = "Movie")
public class MovieDto {
    @Schema(description = "Movie identifier", example = "1")
    int id;
    @Schema(description = "Movie title", example = "Avatar")
    String  title;
    @Schema(description = "Movie director", example = "James Cameroon")
    String director;
    @Schema(description = "Movie genre", example = "Drama")
    String genre;
    @Schema(description = "Movie description", example = " ")
    String description;
    @Schema(description = "Movie duration in minutes", example = "120")
    int duration_min;
}
