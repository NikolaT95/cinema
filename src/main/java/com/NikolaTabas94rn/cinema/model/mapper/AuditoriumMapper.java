package com.NikolaTabas94rn.cinema.model.mapper;

import com.NikolaTabas94rn.cinema.model.api.auditorium.AuditoriumDto;
import com.NikolaTabas94rn.cinema.model.api.auditorium.AuditoriumSaveDto;
import com.NikolaTabas94rn.cinema.model.entity.AuditoriumEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuditoriumMapper {

    AuditoriumDto toDto(AuditoriumEntity auditoriumEntity);

    AuditoriumEntity toEntity(AuditoriumSaveDto auditoriumSaveDto);
}
