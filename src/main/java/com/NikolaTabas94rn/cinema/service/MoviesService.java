package com.NikolaTabas94rn.cinema.service;

import com.NikolaTabas94rn.cinema.exceptions.ErrorInfo;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieDto;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieSaveDto;
import com.NikolaTabas94rn.cinema.model.entity.MovieEntity;
import com.NikolaTabas94rn.cinema.model.mapper.MovieMapper;
import com.NikolaTabas94rn.cinema.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class MoviesService {

    private final MoviesRepository moviesRepository;
    private final MovieMapper movieMapper;

    public List<MovieDto> getAll(){
        return moviesRepository.findAllByOrderByTitleAsc()
                .stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList());
    }

    public MovieDto getOne(int id)throws ResourceNotFoundException {
        MovieEntity movieEntity=moviesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.MOVIE));

        return movieMapper.toDto(movieEntity);
    }

    public MovieDto save(MovieSaveDto movie) throws UniqueViolationException{
        if(moviesRepository.findByTitle(movie.getTitle()).isPresent()){
            throw new UniqueViolationException(ErrorInfo.ResourceType.MOVIE, "'movie' already exists");
        }
        MovieEntity movieEntity=movieMapper.toEntity(movie);
        moviesRepository.save(movieEntity);
        return movieMapper.toDto(movieEntity);
    }

    public MovieDto update(int id, MovieSaveDto updatedMovie)throws UniqueViolationException,ResourceNotFoundException{
        MovieEntity movie=moviesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.MOVIE));

        if(!movie.getTitle().equals(updatedMovie.getTitle())
        && moviesRepository.findByTitle(updatedMovie.getTitle()).isPresent()){
            throw new UniqueViolationException(ErrorInfo.ResourceType.MOVIE, "'movie' already exists");
        }

        MovieEntity updatedMovieEntity=movieMapper.toEntity(updatedMovie);
        updatedMovieEntity.setId(id);

        updatedMovieEntity.setScreenings(movie.getScreenings());

        moviesRepository.save(updatedMovieEntity);

        return movieMapper.toDto(updatedMovieEntity);
    }

    public void remove(int id)throws ResourceNotFoundException{
        if(!moviesRepository.existsById(id)){
            throw new ResourceNotFoundException(ErrorInfo.ResourceType.MOVIE);
        }
        moviesRepository.deleteById(id);
    }

    public List<MovieDto> findByGenre(String genre){
        return moviesRepository.findByGenre(genre)
                .stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList());
    }

    public int getAverageDuration() {
        List<MovieEntity> movies = moviesRepository.findAll();
        int totalDuration = 0;

        for (MovieEntity movie : movies) {
            totalDuration += movie.getDuration_min();
        }

        if (movies.size() > 0) {
            return totalDuration / movies.size();
        } else {
            return 0;
        }
    }
}
