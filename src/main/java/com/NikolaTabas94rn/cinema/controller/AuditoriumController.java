package com.NikolaTabas94rn.cinema.controller;

import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.auditorium.AuditoriumDto;
import com.NikolaTabas94rn.cinema.model.api.auditorium.AuditoriumSaveDto;
import com.NikolaTabas94rn.cinema.model.api.auditorium.AuditoriumSearchOption;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(path="/auditoriums", produces = MediaType.APPLICATION_JSON_VALUE)
public interface AuditoriumController {

    @GetMapping()
    @Operation(description = "Get all auditoriums", summary = "Get all auditoriums")
    Page<AuditoriumDto> getAllAuditoriums(@ParameterObject AuditoriumSearchOption auditoriumSearchOption);

    @GetMapping("/{id}")
    @Operation(description = "Get one auditorium", summary = "Get one auditorium")
    AuditoriumDto getOneAuditorium(@PathVariable int id)throws ResourceNotFoundException;

    @PostMapping
    @Operation(description = "Create auditorium", summary = "Create auditorium")
    AuditoriumDto saveAuditorium(@RequestBody AuditoriumSaveDto auditoriumSaveDto)throws UniqueViolationException;

    @PutMapping("/{id}")
    @Operation(description = "Update auditorium", summary = "Update auditorium")
    AuditoriumDto updateAuditorium(@PathVariable int id, @RequestBody AuditoriumSaveDto auditoriumSaveDto)throws UniqueViolationException, ResourceNotFoundException;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Delete auditorium", summary = "Delete auditorium")
    void removeAuditorium(@PathVariable int id)throws ResourceNotFoundException;
}
