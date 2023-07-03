package com.NikolaTabas94rn.cinema.model.mapper;

import com.NikolaTabas94rn.cinema.model.api.screening.ScreeningDto;
import com.NikolaTabas94rn.cinema.model.api.screening.ScreeningSaveDto;
import com.NikolaTabas94rn.cinema.model.entity.ScreeningEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ScreeningMapper {
    @Mappings({
            @Mapping(source="movie.id", target="movie_id"),
            @Mapping(source="auditorium.id", target="auditorium_id")
    })
    ScreeningDto toDto(ScreeningEntity screeningEntity);

    ScreeningEntity toEntity(ScreeningSaveDto screeningSaveDto);
}
