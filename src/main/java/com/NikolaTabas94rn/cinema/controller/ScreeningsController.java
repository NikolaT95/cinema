package com.NikolaTabas94rn.cinema.controller;

import com.NikolaTabas94rn.cinema.exceptions.BadRequestException;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.screening.ScreeningDto;
import com.NikolaTabas94rn.cinema.model.api.screening.ScreeningSaveDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RequestMapping(path = "/screenings", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ScreeningsController {
    @GetMapping
    List<ScreeningDto> getScreenings();

    @GetMapping("{id}")
    ScreeningDto getScreening(@PathVariable int id)throws ResourceNotFoundException;

    @PostMapping()
    ScreeningDto saveScreening(@RequestBody ScreeningSaveDto saveDto) throws ResourceNotFoundException, UniqueViolationException, BadRequestException;

    @PutMapping("/{id}")
    ScreeningDto updateScreening(@PathVariable int id, @RequestBody ScreeningSaveDto saveDto)throws ResourceNotFoundException, UniqueViolationException;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeScreening(@PathVariable int id)throws ResourceNotFoundException;

    @GetMapping("/movie/{movieId}")
    List<ScreeningDto> getScreeningsByMovie(@PathVariable int movieId)throws ResourceNotFoundException;

    @GetMapping("/datetime")
    List<ScreeningDto> getScreeningsByDateTimeRange(@RequestParam  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CET") Timestamp startTime, @RequestParam  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CET") Timestamp endTime);
}
