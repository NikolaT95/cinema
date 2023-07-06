package com.NikolaTabas94rn.cinema.controller.impl;

import com.NikolaTabas94rn.cinema.controller.ScreeningsController;
import com.NikolaTabas94rn.cinema.exceptions.BadRequestException;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.screening.ScreeningDto;
import com.NikolaTabas94rn.cinema.model.api.screening.ScreeningSaveDto;
import com.NikolaTabas94rn.cinema.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
@RestController
@RequiredArgsConstructor
public class ScreeningsControllerImpl implements ScreeningsController {
    private final ScreeningService screeningService;
    @Override
    public List<ScreeningDto> getScreenings() {
        return screeningService.getALl();
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
    public List<ScreeningDto> getScreeningsByMovie(int movieId) throws ResourceNotFoundException {
        return screeningService.getByMovie(movieId);
    }

    @Override
    public List<ScreeningDto> getScreeningsByDateTimeRange(Timestamp startTime, Timestamp endTime) {
        return screeningService.getByDateTimeRange(startTime,endTime);
    }
}
