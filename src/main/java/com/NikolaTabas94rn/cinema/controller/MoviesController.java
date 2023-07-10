package com.NikolaTabas94rn.cinema.controller;

import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieDto;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieSaveDto;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieSearchOption;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path="/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public interface MoviesController {
    @GetMapping
    @Operation(description = "Get all movies", summary = "Get all movies")
    Page<MovieDto> getMovies(@ParameterObject MovieSearchOption movieSearchOption);

    @GetMapping("/{id}")
    @Operation(description = "Get one movie", summary = "Get one movie")
    MovieDto getMovie(@PathVariable int id)throws ResourceNotFoundException;

    @PostMapping()
    @Operation(description = "Create movie", summary = "Create movie")
    MovieDto saveMovie(@Valid @RequestBody MovieSaveDto movie) throws UniqueViolationException;

    @PutMapping("/{id}")
    @Operation(description = "Update movie", summary = "Update movie")
    MovieDto updateMovie(@PathVariable int id, @Valid @RequestBody MovieSaveDto movie)throws UniqueViolationException,ResourceNotFoundException;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Delete movie", summary = "Delete movie")
    void removeMovie(@PathVariable int id) throws ResourceNotFoundException;

    @GetMapping("/genre/{genre}")
    @Operation(description = "Get movies by genre", summary = "Get movies by genre")
    Page<MovieDto> getMoviesByGenre(@PathVariable String genre,@ParameterObject MovieSearchOption movieSearchOption)throws ResourceNotFoundException;

    @GetMapping("/avg")
    int getAverageMoviesDuration();
}
