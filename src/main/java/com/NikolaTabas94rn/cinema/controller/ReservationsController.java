package com.NikolaTabas94rn.cinema.controller;

import com.NikolaTabas94rn.cinema.exceptions.BadRequestException;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.model.api.reservation.ReservationDto;
import com.NikolaTabas94rn.cinema.model.api.reservation.ReservationSaveDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public interface ReservationsController {
    @GetMapping("/users/{userId}/reservations")
    @Operation(description = "Get all reservations by user", summary = "Get all reservations by user")
    List<ReservationDto> getUsersReservations(@PathVariable int userId) throws ResourceNotFoundException;

    @GetMapping("/screenings/{screeningId}/reservations")
    @Operation(description = "Get all reservations by screening", summary = "Get all reservations by screening")
    List<ReservationDto> getReservationsForScreening(@PathVariable int screeningId) throws ResourceNotFoundException;

    @GetMapping("/screenings/{screeningId}/reservation/{userId}")
    @Operation(description = "Get user's reservation for screening", summary = "Get user's reservation for screening")
    ReservationDto getUsersReservationForScreening(@PathVariable int screeningId, @PathVariable int userId ) throws ResourceNotFoundException;

    @PostMapping("/screenings/{screeningId}/reservation/{userId}")
    @Operation(description = "Create user's reservation for screening", summary = "Create user's reservation for screening")
    ReservationDto saveReservation(@PathVariable int screeningId, @PathVariable int userId, @Valid @RequestBody ReservationSaveDto reservationSaveDto) throws BadRequestException, ResourceNotFoundException;

    @DeleteMapping("/screenings/{screeningId}/reservation/{userId}")
    @Operation(description = "Delete user's reservation for screening", summary = "Delete user's reservation for screening")
    void removeReservation(@PathVariable int screeningId, @PathVariable int userId) throws ResourceNotFoundException;
}
