package com.NikolaTabas94rn.cinema.controller;

import com.NikolaTabas94rn.cinema.exceptions.BadRequestException;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.screening.ScreeningDto;
import com.NikolaTabas94rn.cinema.model.api.screening.ScreeningSaveDto;
import com.NikolaTabas94rn.cinema.model.api.screening.ScreeningSearchOption;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RequestMapping(path = "/screenings", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ScreeningsController {
    @GetMapping
    @Operation(description = "Get all screenings", summary = "Get all screenings")
    Page<ScreeningDto> getScreenings(@ParameterObject ScreeningSearchOption screeningSearchOption);

    @GetMapping("{id}")
    @Operation(description = "Get one screening", summary = "Get one screening")
    ScreeningDto getScreening(@PathVariable int id)throws ResourceNotFoundException;

    @PostMapping()
    @Operation(description = "Create screening", summary = "Create screening")
    ScreeningDto saveScreening(@RequestBody ScreeningSaveDto saveDto) throws ResourceNotFoundException, UniqueViolationException, BadRequestException;

    @PutMapping("/{id}")
    @Operation(description = "Update screening", summary = "Update screening")
    ScreeningDto updateScreening(@PathVariable int id, @RequestBody ScreeningSaveDto saveDto)throws ResourceNotFoundException, UniqueViolationException;

    @DeleteMapping("/{id}")
    @Operation(description = "Delete screening", summary = "Delete screening")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeScreening(@PathVariable int id)throws ResourceNotFoundException;

    @GetMapping("/movie/{movieId}")
    @Operation(description = "Get all screenings for a movie", summary = "Get all screenings for a movie")
    Page<ScreeningDto> getScreeningsByMovie(@PathVariable int movieId, @ParameterObject ScreeningSearchOption screeningSearchOption)throws ResourceNotFoundException;

    @GetMapping("/datetime")
    @Operation(description = "Get all screenings for time range", summary = "Get all screenings for time range")
    Page<ScreeningDto> getScreeningsByDateTimeRange(@RequestParam  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CET") Timestamp startTime, @RequestParam  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CET") Timestamp endTime, @ParameterObject ScreeningSearchOption screeningSearchOption);
}
