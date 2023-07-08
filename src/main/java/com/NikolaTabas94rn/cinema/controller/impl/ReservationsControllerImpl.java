package com.NikolaTabas94rn.cinema.controller.impl;

import com.NikolaTabas94rn.cinema.controller.ReservationsController;
import com.NikolaTabas94rn.cinema.exceptions.BadRequestException;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.model.api.reservation.ReservationDto;
import com.NikolaTabas94rn.cinema.model.api.reservation.ReservationSaveDto;
import com.NikolaTabas94rn.cinema.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationsControllerImpl implements ReservationsController {
    private final ReservationService reservationService;
    @Override
    public List<ReservationDto> getUsersReservations(int userId) throws ResourceNotFoundException {
        return reservationService.getUsersReservations(userId);
    }

    @Override
    public List<ReservationDto> getReservationsForScreening(int screeningId) throws ResourceNotFoundException {
        return reservationService.getScreeningsReservations(screeningId);
    }

    @Override
    public ReservationDto getUsersReservationForScreening(int screeningId, int userId) throws ResourceNotFoundException {
        return reservationService.getScreeningForUser(screeningId,userId);
    }

    @Override
    public ReservationDto saveReservation(int screeningId, int userId, ReservationSaveDto reservationSaveDto) throws BadRequestException, ResourceNotFoundException {
        return reservationService.save(screeningId,userId,reservationSaveDto);
    }

    @Override
    public void removeReservation(int screeningId, int userId) throws ResourceNotFoundException {
        reservationService.remove(screeningId,userId);
    }
}
