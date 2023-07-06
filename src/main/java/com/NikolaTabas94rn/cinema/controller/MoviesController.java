package com.NikolaTabas94rn.cinema.controller;

import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieDto;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieSaveDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path="/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public interface MoviesController {
    @GetMapping
    List<MovieDto> getMovies();

    @GetMapping("/{id}")
    MovieDto getMovie(@PathVariable int id)throws ResourceNotFoundException;

    @PostMapping()
    MovieDto saveMovie(@RequestBody MovieSaveDto movie) throws UniqueViolationException;

    @PutMapping("/{id}")
    MovieDto updateMovie(@PathVariable int id, @RequestBody MovieSaveDto movie)throws UniqueViolationException,ResourceNotFoundException;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeMovie(@PathVariable int id) throws ResourceNotFoundException;

    @GetMapping("/genre/{genre}")
    List<MovieDto> getMoviesByGenre(@PathVariable String genre)throws ResourceNotFoundException;

    @GetMapping("/avg")
    int getAverageMoviesDuration();
}
