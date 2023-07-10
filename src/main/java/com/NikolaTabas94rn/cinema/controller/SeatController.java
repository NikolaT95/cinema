package com.NikolaTabas94rn.cinema.controller;

import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieDto;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieSaveDto;
import com.NikolaTabas94rn.cinema.model.api.seat.SeatDto;
import com.NikolaTabas94rn.cinema.model.api.seat.SeatSaveDto;
import com.NikolaTabas94rn.cinema.model.api.seat.SeatSearchOption;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path="/seats", produces = MediaType.APPLICATION_JSON_VALUE)
public interface SeatController {
    @GetMapping
    @Operation(description = "Get all seats", summary = "Get all seats")
    Page<SeatDto> getSeats(@ParameterObject SeatSearchOption seatSearchOption);

    @GetMapping("/{id}")
    @Operation(description = "Get one seat", summary = "Get one seat")
    SeatDto getSeat(@PathVariable int id)throws ResourceNotFoundException;

    @PostMapping()
    @Operation(description = "Create seat", summary = "Create seat")
    SeatDto saveSeat(@Valid @RequestBody SeatSaveDto movie) throws UniqueViolationException, ResourceNotFoundException;

    @PutMapping("/{id}")
    @Operation(description = "Update seat", summary = "Update seat")
    SeatDto updateSeat(@PathVariable int id, @Valid @RequestBody SeatSaveDto movie)throws UniqueViolationException,ResourceNotFoundException;

    @DeleteMapping("/{id}")
    @Operation(description = "Delete seat", summary = "Delete seat")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeSeat(@PathVariable int id) throws ResourceNotFoundException;

}
