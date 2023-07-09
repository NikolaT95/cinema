package com.NikolaTabas94rn.cinema.controller.impl;

import com.NikolaTabas94rn.cinema.controller.MoviesController;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieDto;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieSaveDto;
import com.NikolaTabas94rn.cinema.service.MoviesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@Tag(name = "Movies")
@RequiredArgsConstructor
public class MoviesControllerImpl implements MoviesController {
    private final MoviesService moviesService;
    @Override
    public List<MovieDto> getMovies() {
        return moviesService.getAll();
    }

    @Override
    public MovieDto getMovie(int id) throws ResourceNotFoundException {
        return moviesService.getOne(id);
    }

    @Override
    public MovieDto saveMovie(MovieSaveDto movie) throws UniqueViolationException {
        return moviesService.save(movie);
    }

    @Override
    public MovieDto updateMovie(int id, MovieSaveDto movie) throws UniqueViolationException, ResourceNotFoundException {
        return moviesService.update(id, movie);
    }

    @Override
    public void removeMovie(int id) throws ResourceNotFoundException {
        moviesService.remove(id);
    }

    @Override
    public List<MovieDto> getMoviesByGenre(String genre) throws ResourceNotFoundException {
        return moviesService.findByGenre(genre);
    }

    @Override
    public int getAverageMoviesDuration() {
        return moviesService.getAverageDuration();
    }
}
