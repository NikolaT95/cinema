package com.NikolaTabas94rn.cinema.service;

import com.NikolaTabas94rn.cinema.exceptions.BadRequestException;
import com.NikolaTabas94rn.cinema.exceptions.ErrorInfo;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.screening.ScreeningDto;
import com.NikolaTabas94rn.cinema.model.api.screening.ScreeningSaveDto;
import com.NikolaTabas94rn.cinema.model.entity.AuditoriumEntity;
import com.NikolaTabas94rn.cinema.model.entity.MovieEntity;
import com.NikolaTabas94rn.cinema.model.entity.ScreeningEntity;
import com.NikolaTabas94rn.cinema.model.mapper.ScreeningMapper;
import com.NikolaTabas94rn.cinema.repository.AuditoriumsRepository;
import com.NikolaTabas94rn.cinema.repository.MoviesRepository;
import com.NikolaTabas94rn.cinema.repository.ScreeningsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final ScreeningsRepository screeningsRepository;
    private final ScreeningMapper screeningMapper;
    private final MoviesRepository moviesRepository;
    private final AuditoriumsRepository auditoriumsRepository;

    public List<ScreeningDto> getALl(){
        return screeningsRepository.findAllByOrderByScreeningStart()
                .stream()
                .map(screeningMapper::toDto)
                .collect(Collectors.toList());
    }

    public ScreeningDto getOne(int id)throws ResourceNotFoundException{
        ScreeningEntity screeningEntity=screeningsRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.SCREENING));

        return screeningMapper.toDto(screeningEntity);
    }

    public ScreeningDto save(ScreeningSaveDto screeningSaveDto) throws ResourceNotFoundException, BadRequestException, UniqueViolationException {
        MovieEntity movie=getMovie(screeningSaveDto.getMovie_id());
        AuditoriumEntity auditorium=getAuditorium(screeningSaveDto.getAuditorium_id());

        if(screeningsRepository.findByScreeningStartAndAuditorium(screeningSaveDto.getScreeningStart(),auditorium).isPresent()){
            throw new UniqueViolationException(ErrorInfo.ResourceType.SCREENING, "'screening' already exists");
        }

        ScreeningEntity screeningEntity=screeningMapper.toEntity(screeningSaveDto);
        screeningEntity.setMovie(movie);
        screeningEntity.setAuditorium(auditorium);
        screeningsRepository.save(screeningEntity);


        return screeningMapper.toDto(screeningEntity);
    }

    private AuditoriumEntity getAuditorium(int auditorium_id)throws ResourceNotFoundException {
        return auditoriumsRepository.findById(auditorium_id).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.AUDITORIUM));
    }

    private MovieEntity getMovie(int movie_id)throws ResourceNotFoundException {
        return moviesRepository.findById(movie_id).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.MOVIE));
    }


    public ScreeningDto update(int id, ScreeningSaveDto updatedScreening) throws ResourceNotFoundException, UniqueViolationException {
        ScreeningEntity screening=screeningsRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.SCREENING));

        MovieEntity movie=getMovie(updatedScreening.getMovie_id());
        AuditoriumEntity auditorium=getAuditorium(updatedScreening.getAuditorium_id());

         if(!screening.getScreeningStart().equals(updatedScreening.getScreeningStart())
                && !screening.getAuditorium().equals(updatedScreening.getAuditorium_id()) && screeningsRepository.findByScreeningStartAndAuditorium(updatedScreening.getScreeningStart(), auditorium).isPresent()){
            throw new UniqueViolationException(ErrorInfo.ResourceType.SCREENING, "'screening' already exists");
        }

        ScreeningEntity updatedScreeningEntity=screeningMapper.toEntity(updatedScreening);
        updatedScreeningEntity.setId(id);
        updatedScreeningEntity.setMovie(movie);
        updatedScreeningEntity.setAuditorium(auditorium);

        screeningsRepository.save(updatedScreeningEntity);

        return  screeningMapper.toDto(updatedScreeningEntity);
    }

    public void remove(int id)throws  ResourceNotFoundException{
        if(!screeningsRepository.existsById(id)){
            throw new ResourceNotFoundException(ErrorInfo.ResourceType.SCREENING);
        }
        screeningsRepository.deleteById(id);
    }
    public List<ScreeningDto> getByMovie(int movieId) throws ResourceNotFoundException {
        MovieEntity movie=getMovie(movieId);
        return movie.getScreenings()
                .stream()
                .map(screeningMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ScreeningDto> getByDateTimeRange(Timestamp startTime, Timestamp endTime) {
        List<ScreeningEntity> screenings = screeningsRepository.findByScreeningStartBetween(startTime, endTime);
        return screenings.stream()
                .map(screeningMapper::toDto)
                .collect(Collectors.toList());
    }
}
