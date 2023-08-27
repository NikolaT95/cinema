package com.NikolaTabas94rn.cinema.model.api.movie;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
@Builder
@Schema(name = "MovieSave")
public class MovieSaveDto {
    @Schema(description = "Movie title", example = "Avatar")
    @NotBlank
    String  title;
    @Schema(description = "Movie director", example = "James Cameroon")
    @NotBlank
    String director;
    @Schema(description = "Movie genre", example = "Drama")
    @NotBlank
    String genre;
    @Schema(description = "Movie description", example = " ")
    String description;
    @Schema(description = "Movie duration in minutes", example = "120")
    @NotNull
    int duration_min;
}
