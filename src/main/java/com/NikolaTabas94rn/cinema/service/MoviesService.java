package com.NikolaTabas94rn.cinema.service;

import com.NikolaTabas94rn.cinema.exceptions.ErrorInfo;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieDto;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieSaveDto;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieSearchOption;
import com.NikolaTabas94rn.cinema.model.entity.MovieEntity;
import com.NikolaTabas94rn.cinema.model.mapper.MovieMapper;
import com.NikolaTabas94rn.cinema.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class MoviesService {

    private final MoviesRepository moviesRepository;
    private final MovieMapper movieMapper;

    public Page<MovieDto> getAll(MovieSearchOption movieSearchOption){
        int page = 1;
        if(movieSearchOption.getPage() != null && movieSearchOption.getPage() > 0) {
            page = movieSearchOption.getPage() - 1;
        }

        int pageSize = 10;
        if(movieSearchOption.getPageSize() != null && movieSearchOption.getPageSize() > 0) {
            pageSize = movieSearchOption.getPageSize();
        }
        return moviesRepository.findAllByOrderByTitleAsc(PageRequest.of(page,pageSize))
                .map(movieMapper::toDto);
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

    public Page<MovieDto> findByGenre(String genre, MovieSearchOption movieSearchOption){
        int page = 1;
        if(movieSearchOption.getPage() != null && movieSearchOption.getPage() > 0) {
            page = movieSearchOption.getPage() - 1;
        }

        int pageSize = 10;
        if(movieSearchOption.getPageSize() != null && movieSearchOption.getPageSize() > 0) {
            pageSize = movieSearchOption.getPageSize();
        }
        return moviesRepository.findByGenre(genre,PageRequest.of(page,pageSize))
                .map(movieMapper::toDto);
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
