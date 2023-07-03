package com.NikolaTabas94rn.cinema.model.mapper;

import com.NikolaTabas94rn.cinema.model.api.movie.MovieDto;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieSaveDto;
import com.NikolaTabas94rn.cinema.model.entity.MovieEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDto toDto(MovieEntity movieEntity);

    MovieEntity toEntity(MovieSaveDto movieSaveDto);
}
