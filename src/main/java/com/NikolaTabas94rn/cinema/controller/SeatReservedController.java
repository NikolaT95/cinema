package com.NikolaTabas94rn.cinema.controller;

import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.model.api.seatReserved.SeatReservedDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(path = "/seats-reserved",produces = MediaType.APPLICATION_JSON_VALUE)
public interface SeatReservedController {
    @Operation(description = "Get all reserved seats for a reservation", summary = "Get all reservations reserved seats for a reservation")
    @GetMapping("/reservation/{reservationId}")
    List<SeatReservedDto> getReservationReservedSeats(@PathVariable int reservationId) throws ResourceNotFoundException;
    @Operation(description = "Get all reserved seats for a screening", summary = "Get all reservations reserved seats for a screening")
    @GetMapping("/screening/{screeningId}")
    List<SeatReservedDto> getScreeningReservedSeats(@PathVariable int screeningId) throws ResourceNotFoundException;
}
