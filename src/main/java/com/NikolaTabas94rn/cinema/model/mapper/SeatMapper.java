package com.NikolaTabas94rn.cinema.model.mapper;

import com.NikolaTabas94rn.cinema.model.api.seat.SeatDto;
import com.NikolaTabas94rn.cinema.model.api.seat.SeatSaveDto;
import com.NikolaTabas94rn.cinema.model.entity.SeatEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    @Mapping(source = "auditorium.id", target="auditorium_id")
    SeatDto toDto(SeatEntity seatEntity);

    SeatEntity toEntity(SeatSaveDto seatSaveDto);
}
