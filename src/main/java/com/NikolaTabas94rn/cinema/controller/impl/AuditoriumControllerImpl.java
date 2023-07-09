package com.NikolaTabas94rn.cinema.controller.impl;

import com.NikolaTabas94rn.cinema.controller.AuditoriumController;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.auditorium.AuditoriumDto;
import com.NikolaTabas94rn.cinema.model.api.auditorium.AuditoriumSaveDto;
import com.NikolaTabas94rn.cinema.model.api.auditorium.AuditoriumSearchOption;
import com.NikolaTabas94rn.cinema.service.AuditoriumService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@Tag(name = "Auditoriums")
@RequiredArgsConstructor
public class AuditoriumControllerImpl implements AuditoriumController {
    private final AuditoriumService auditoriumService;
    @Override
    public Page<AuditoriumDto> getAllAuditoriums(AuditoriumSearchOption auditoriumSearchOption) {
        return auditoriumService.getAll(auditoriumSearchOption);
    }

    @Override
    public AuditoriumDto getOneAuditorium(int id) throws ResourceNotFoundException {
        return auditoriumService.getOne(id);
    }

    @Override
    public AuditoriumDto saveAuditorium(AuditoriumSaveDto auditoriumSaveDto) throws UniqueViolationException {
        return auditoriumService.save(auditoriumSaveDto);
    }

    @Override
    public AuditoriumDto updateAuditorium(int id, AuditoriumSaveDto auditoriumSaveDto) throws UniqueViolationException, ResourceNotFoundException {
        return auditoriumService.update(id,auditoriumSaveDto);
    }

    @Override
    public void removeAuditorium(int id) throws ResourceNotFoundException {
        auditoriumService.remove(id);
    }
}
