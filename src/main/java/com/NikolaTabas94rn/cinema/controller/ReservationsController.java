package com.NikolaTabas94rn.cinema.controller;

import com.NikolaTabas94rn.cinema.exceptions.BadRequestException;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.model.api.reservation.ReservationDto;
import com.NikolaTabas94rn.cinema.model.api.reservation.ReservationSaveDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public interface ReservationsController {
    @GetMapping("/users/{userId}/reservations")
    List<ReservationDto> getUsersReservations(@PathVariable int userId) throws ResourceNotFoundException;

    @GetMapping("/screenings/{screeningId}/reservations")
    List<ReservationDto> getReservationsForScreening(@PathVariable int screeningId) throws ResourceNotFoundException;

    @GetMapping("/screenings/{screeningId}/reservation/{userId}")
    ReservationDto getUsersReservationForScreening(@PathVariable int screeningId, @PathVariable int userId ) throws ResourceNotFoundException;

    @PostMapping("/screenings/{screeningId}/reservation/{userId}")
    ReservationDto saveReservation(@PathVariable int screeningId, @PathVariable int userId, @RequestBody ReservationSaveDto reservationSaveDto) throws BadRequestException, ResourceNotFoundException;

    @DeleteMapping("/screenings/{screeningId}/reservation/{userId}")
    void removeReservation(@PathVariable int screeningId, @PathVariable int userId) throws ResourceNotFoundException;
}
