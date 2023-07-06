package com.NikolaTabas94rn.cinema.controller;

import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.auditorium.AuditoriumDto;
import com.NikolaTabas94rn.cinema.model.api.auditorium.AuditoriumSaveDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(path="/auditoriums", produces = MediaType.APPLICATION_JSON_VALUE)
public interface AuditoriumController {

    @GetMapping
    List<AuditoriumDto> getAllAuditoriums();

    @GetMapping("/{id}")
    AuditoriumDto getOneAuditorium(@PathVariable int id)throws ResourceNotFoundException;

    @PostMapping
    AuditoriumDto saveAuditorium(@RequestBody AuditoriumSaveDto auditoriumSaveDto)throws UniqueViolationException;

    @PutMapping("/{id}")
    AuditoriumDto updateAuditorium(@PathVariable int id, @RequestBody AuditoriumSaveDto auditoriumSaveDto)throws UniqueViolationException, ResourceNotFoundException;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeAuditorium(@PathVariable int id)throws ResourceNotFoundException;
}
