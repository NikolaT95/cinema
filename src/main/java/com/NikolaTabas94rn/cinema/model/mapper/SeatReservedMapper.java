package com.NikolaTabas94rn.cinema.model.mapper;

import com.NikolaTabas94rn.cinema.model.api.seat.SeatDto;
import com.NikolaTabas94rn.cinema.model.api.seatReserved.SeatReservedDto;
import com.NikolaTabas94rn.cinema.model.api.seatReserved.SeatReservedSaveDto;
import com.NikolaTabas94rn.cinema.model.entity.SeatEntity;
import com.NikolaTabas94rn.cinema.model.entity.SeatReservedEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SeatReservedMapper {
    @Mappings({
            @Mapping(source="reservation.id",target = "reservation_id"),
            @Mapping(source="seat.id",target = "seat_id"),
            @Mapping(source="screening.id",target = "screening_id")
    })
    SeatReservedDto toDto(SeatReservedEntity seatReservedEntity);

    SeatReservedEntity toEntity(SeatReservedSaveDto seatReservedSaveDto);
}
