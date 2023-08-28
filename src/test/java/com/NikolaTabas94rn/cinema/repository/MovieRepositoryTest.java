package com.NikolaTabas94rn.cinema.repository;

import com.NikolaTabas94rn.cinema.model.entity.MovieEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieRepositoryTest {

    @Mock
    private MoviesRepository moviesRepository;

    @Test
    public void testFindAll() {

        List<MovieEntity> expectedMovies = Arrays.asList(
                new MovieEntity(1, "Movie 1", "Director 1", "Action", "Description 1", 120, new ArrayList<>()),
                new MovieEntity(2, "Movie 2", "Director 2", "Comedy", "Description 2", 90, new ArrayList<>())
        );

        when(moviesRepository.findAll()).thenReturn(expectedMovies);


        List<MovieEntity> actualMovies = moviesRepository.findAll();


        assertEquals(expectedMovies, actualMovies);
    }

    @Test
    public void testFindByTitle() {

        String title = "Movie 1";
        MovieEntity expectedMovie = new MovieEntity(1, title, "Director 1", "Action", "Description 1", 120, new ArrayList<>());

        when(moviesRepository.findByTitle(title)).thenReturn(Optional.of(expectedMovie));


        Optional<MovieEntity> actualMovieOptional = moviesRepository.findByTitle(title);


        assertTrue(actualMovieOptional.isPresent());
        assertEquals(expectedMovie, actualMovieOptional.get());
    }

    @Test
    public void testFindByGenre() {

        String genre = "Action";
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<MovieEntity> expectedMovies = Arrays.asList(
                new MovieEntity(1, "Movie 1", "Director 1", "Action", "Description 1", 120, new ArrayList<>()),
                new MovieEntity(2, "Movie 2", "Director 2", "Action", "Description 2", 110, new ArrayList<>())
        );
        Page<MovieEntity> expectedPage = new PageImpl<>(expectedMovies, pageRequest, expectedMovies.size());

        when(moviesRepository.findByGenre(eq(genre), any(PageRequest.class))).thenReturn(expectedPage);


        Page<MovieEntity> actualPage = moviesRepository.findByGenre(genre, pageRequest);


        assertEquals(expectedPage, actualPage);
    }
}
