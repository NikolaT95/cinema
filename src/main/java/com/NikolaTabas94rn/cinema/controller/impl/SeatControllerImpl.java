package com.NikolaTabas94rn.cinema.controller.impl;

import com.NikolaTabas94rn.cinema.controller.SeatController;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.seat.SeatDto;
import com.NikolaTabas94rn.cinema.model.api.seat.SeatSaveDto;
import com.NikolaTabas94rn.cinema.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class SeatControllerImpl implements SeatController {
    private final SeatService seatService;
    @Override
    public List<SeatDto> getSeats() {
        return seatService.getAll();
    }

    @Override
    public SeatDto getSeat(int id) throws ResourceNotFoundException {
        return seatService.getOne(id);
    }

    @Override
    public SeatDto saveSeat(SeatSaveDto movie) throws UniqueViolationException, ResourceNotFoundException {
        return seatService.save(movie);
    }

    @Override
    public SeatDto updateSeat(int id, SeatSaveDto movie) throws UniqueViolationException, ResourceNotFoundException {
        return seatService.update(id, movie);
    }

    @Override
    public void removeSeat(int id) throws ResourceNotFoundException {
        seatService.remove(id);
    }
}
