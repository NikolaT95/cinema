package com.NikolaTabas94rn.cinema.controller;

import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieDto;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieSaveDto;
import com.NikolaTabas94rn.cinema.model.api.seat.SeatDto;
import com.NikolaTabas94rn.cinema.model.api.seat.SeatSaveDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path="/seats", produces = MediaType.APPLICATION_JSON_VALUE)
public interface SeatController {
    @GetMapping
    @Operation(description = "Get all seats", summary = "Get all seats")
    List<SeatDto> getSeats();

    @GetMapping("/{id}")
    @Operation(description = "Get one seat", summary = "Get one seat")
    SeatDto getSeat(@PathVariable int id)throws ResourceNotFoundException;

    @PostMapping()
    @Operation(description = "Create seat", summary = "Create seat")
    SeatDto saveSeat(@RequestBody SeatSaveDto movie) throws UniqueViolationException, ResourceNotFoundException;

    @PutMapping("/{id}")
    @Operation(description = "Update seat", summary = "Update seat")
    SeatDto updateSeat(@PathVariable int id, @RequestBody SeatSaveDto movie)throws UniqueViolationException,ResourceNotFoundException;

    @DeleteMapping("/{id}")
    @Operation(description = "Delete seat", summary = "Delete seat")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeSeat(@PathVariable int id) throws ResourceNotFoundException;

}
