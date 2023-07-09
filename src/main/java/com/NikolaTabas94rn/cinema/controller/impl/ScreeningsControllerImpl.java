package com.NikolaTabas94rn.cinema.controller.impl;

import com.NikolaTabas94rn.cinema.controller.ScreeningsController;
import com.NikolaTabas94rn.cinema.exceptions.BadRequestException;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.screening.ScreeningDto;
import com.NikolaTabas94rn.cinema.model.api.screening.ScreeningSaveDto;
import com.NikolaTabas94rn.cinema.model.api.screening.ScreeningSearchOption;
import com.NikolaTabas94rn.cinema.service.ScreeningService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
@RestController
@Tag(name = "Screenings")
@RequiredArgsConstructor
public class ScreeningsControllerImpl implements ScreeningsController {
    private final ScreeningService screeningService;
    @Override
    public Page<ScreeningDto> getScreenings(@ParameterObject ScreeningSearchOption screeningSearchOption) {
        return screeningService.getALl(screeningSearchOption);
    }

    @Override
    public ScreeningDto getScreening(int id) throws ResourceNotFoundException {
        return screeningService.getOne(id);
    }

    @Override
    public ScreeningDto saveScreening(ScreeningSaveDto saveDto) throws ResourceNotFoundException, UniqueViolationException, BadRequestException {
        return screeningService.save(saveDto);
    }

    @Override
    public ScreeningDto updateScreening(int id, ScreeningSaveDto saveDto) throws ResourceNotFoundException, UniqueViolationException {
        return screeningService.update(id,saveDto) ;
    }

    @Override
    public void removeScreening(int id) throws ResourceNotFoundException {
        screeningService.remove(id);
    }

    @Override
    public Page<ScreeningDto> getScreeningsByMovie(int movieId, ScreeningSearchOption screeningSearchOption) throws ResourceNotFoundException {
        return screeningService.getByMovie(movieId, screeningSearchOption);
    }

    @Override
    public Page<ScreeningDto> getScreeningsByDateTimeRange(Timestamp startTime, Timestamp endTime, ScreeningSearchOption screeningSearchOption) {
        return screeningService.getByDateTimeRange(startTime,endTime, screeningSearchOption);
    }
}
