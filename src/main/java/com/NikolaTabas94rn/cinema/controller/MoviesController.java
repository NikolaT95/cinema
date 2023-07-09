package com.NikolaTabas94rn.cinema.controller;

import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieDto;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieSaveDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path="/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public interface MoviesController {
    @GetMapping
    @Operation(description = "Get all movies", summary = "Get all movies")
    List<MovieDto> getMovies();

    @GetMapping("/{id}")
    @Operation(description = "Get one movie", summary = "Get one movie")
    MovieDto getMovie(@PathVariable int id)throws ResourceNotFoundException;

    @PostMapping()
    @Operation(description = "Create movie", summary = "Create movie")
    MovieDto saveMovie(@RequestBody MovieSaveDto movie) throws UniqueViolationException;

    @PutMapping("/{id}")
    @Operation(description = "Update movie", summary = "Update movie")
    MovieDto updateMovie(@PathVariable int id, @RequestBody MovieSaveDto movie)throws UniqueViolationException,ResourceNotFoundException;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Delete movie", summary = "Delete movie")
    void removeMovie(@PathVariable int id) throws ResourceNotFoundException;

    @GetMapping("/genre/{genre}")
    @Operation(description = "Get movies by genre", summary = "Get movies by genre")
    List<MovieDto> getMoviesByGenre(@PathVariable String genre)throws ResourceNotFoundException;

    @GetMapping("/avg")
    int getAverageMoviesDuration();
}
