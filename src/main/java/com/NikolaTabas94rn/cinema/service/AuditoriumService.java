package com.NikolaTabas94rn.cinema.service;

import com.NikolaTabas94rn.cinema.exceptions.ErrorInfo;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.auditorium.AuditoriumDto;
import com.NikolaTabas94rn.cinema.model.api.auditorium.AuditoriumSaveDto;
import com.NikolaTabas94rn.cinema.model.api.movie.MovieSaveDto;
import com.NikolaTabas94rn.cinema.model.entity.AuditoriumEntity;
import com.NikolaTabas94rn.cinema.model.entity.MovieEntity;
import com.NikolaTabas94rn.cinema.model.mapper.AuditoriumMapper;
import com.NikolaTabas94rn.cinema.repository.AuditoriumsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class AuditoriumService {
    private final AuditoriumsRepository auditoriumsRepository;
    private final AuditoriumMapper auditoriumMapper;

    public List<AuditoriumDto> getAll(){
        return auditoriumsRepository.findAll()
                .stream()
                .map(auditoriumMapper::toDto)
                .collect(Collectors.toList());
    }

    public AuditoriumDto getOne(int id)throws ResourceNotFoundException{
        AuditoriumEntity auditoriumEntity=auditoriumsRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.AUDITORIUM));

        return auditoriumMapper.toDto(auditoriumEntity);
    }

    public AuditoriumDto save(AuditoriumSaveDto auditorium)throws UniqueViolationException{
        if(auditoriumsRepository.findByName(auditorium.getName()).isPresent()){
            throw new UniqueViolationException(ErrorInfo.ResourceType.AUDITORIUM, "'auditorium' already exists");
        }
        AuditoriumEntity auditoriumEntity=auditoriumMapper.toEntity(auditorium);
        auditoriumsRepository.save(auditoriumEntity);
        return auditoriumMapper.toDto(auditoriumEntity);
    }

    public AuditoriumDto update(int id, AuditoriumSaveDto updatedAuditorium)throws UniqueViolationException,ResourceNotFoundException{
        AuditoriumEntity auditorium=auditoriumsRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.AUDITORIUM));

        if(!auditorium.getName().equals(updatedAuditorium.getName())
        && auditoriumsRepository.findByName(updatedAuditorium.getName()).isPresent()){
            throw new UniqueViolationException(ErrorInfo.ResourceType.AUDITORIUM, "'auditorium' already exists");
        }

        AuditoriumEntity updatedAuditoriumEntity=auditoriumMapper.toEntity(updatedAuditorium);
        updatedAuditoriumEntity.setId(id);

        updatedAuditoriumEntity.setScreenings(auditorium.getScreenings());

        auditoriumsRepository.save(updatedAuditoriumEntity);

        return auditoriumMapper.toDto(updatedAuditoriumEntity);
    }

    public void remove(int id)throws ResourceNotFoundException{
        if(!auditoriumsRepository.existsById(id)){
            throw new ResourceNotFoundException(ErrorInfo.ResourceType.AUDITORIUM);
        }
        auditoriumsRepository.deleteById(id);
    }
}
