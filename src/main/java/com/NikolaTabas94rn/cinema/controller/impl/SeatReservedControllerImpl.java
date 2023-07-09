package com.NikolaTabas94rn.cinema.controller.impl;

import com.NikolaTabas94rn.cinema.controller.SeatReservedController;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.model.api.seatReserved.SeatReservedDto;
import com.NikolaTabas94rn.cinema.service.SeatReservedService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Seats Reserved")
@RequiredArgsConstructor
public class SeatReservedControllerImpl implements SeatReservedController {
    private final SeatReservedService seatReservedService;
    @Override
    public List<SeatReservedDto> getReservationReservedSeats(int reservationId) throws ResourceNotFoundException {
        return seatReservedService.getReservationReservedSeats(reservationId);
    }

    @Override
    public List<SeatReservedDto> getScreeningReservedSeats(int screeningId) throws ResourceNotFoundException {
        return seatReservedService.getScreeningReservedSeats(screeningId);
    }
}
