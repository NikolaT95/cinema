package com.NikolaTabas94rn.cinema.controller;

import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieDto;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieSaveDto;
import com.NikolaTabas94rn.cinema.model.api.seat.SeatDto;
import com.NikolaTabas94rn.cinema.model.api.seat.SeatSaveDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path="/seats", produces = MediaType.APPLICATION_JSON_VALUE)
public interface SeatController {
    @GetMapping
    List<SeatDto> getSeats();

    @GetMapping("/{id}")
    SeatDto getSeat(@PathVariable int id)throws ResourceNotFoundException;

    @PostMapping()
    SeatDto saveSeat(@RequestBody SeatSaveDto movie) throws UniqueViolationException, ResourceNotFoundException;

    @PutMapping("/{id}")
    SeatDto updateSeat(@PathVariable int id, @RequestBody SeatSaveDto movie)throws UniqueViolationException,ResourceNotFoundException;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeSeat(@PathVariable int id) throws ResourceNotFoundException;

}
