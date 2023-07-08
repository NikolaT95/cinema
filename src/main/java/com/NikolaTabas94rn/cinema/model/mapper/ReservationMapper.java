package com.NikolaTabas94rn.cinema.model.mapper;

import com.NikolaTabas94rn.cinema.model.api.reservation.ReservationDto;
import com.NikolaTabas94rn.cinema.model.api.reservation.ReservationSaveDto;
import com.NikolaTabas94rn.cinema.model.entity.ReservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    @Mappings({
            @Mapping(source = "screening.id", target ="screening_id"),
            @Mapping(source = "userReserved.id",target = "user_reserved_id")
    })
    ReservationDto toDto(ReservationEntity reservationEntity);

    ReservationEntity toEntity(ReservationSaveDto reservationSaveDto);
}
