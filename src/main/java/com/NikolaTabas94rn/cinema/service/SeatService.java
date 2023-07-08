package com.NikolaTabas94rn.cinema.service;

import com.NikolaTabas94rn.cinema.exceptions.ErrorInfo;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.exceptions.UniqueViolationException;
import com.NikolaTabas94rn.cinema.model.api.seat.SeatDto;
import com.NikolaTabas94rn.cinema.model.api.seat.SeatSaveDto;
import com.NikolaTabas94rn.cinema.model.entity.AuditoriumEntity;
import com.NikolaTabas94rn.cinema.model.entity.SeatEntity;
import com.NikolaTabas94rn.cinema.model.mapper.SeatMapper;
import com.NikolaTabas94rn.cinema.repository.AuditoriumsRepository;
import com.NikolaTabas94rn.cinema.repository.SeatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatsRepository seatsRepository;
    private final SeatMapper seatMapper;

    private final AuditoriumsRepository auditoriumsRepository;

    public List<SeatDto> getAll(){
        return seatsRepository.findAll()
                .stream()
                .map(seatMapper::toDto)
                .collect(Collectors.toList());
    }

    public SeatDto getOne(int id)throws ResourceNotFoundException{
        SeatEntity seatEntity=seatsRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.SEAT));

        return seatMapper.toDto(seatEntity);
    }

    public SeatDto save(SeatSaveDto seat) throws UniqueViolationException, ResourceNotFoundException {
        AuditoriumEntity auditorium=getAuditorium(seat.getAuditorium_id());

        if(seatsRepository.findByRowAndNumberAndAuditorium(seat.getRow(),seat.getNumber(),auditorium).isPresent()){
            throw new UniqueViolationException(ErrorInfo.ResourceType.SEAT, "'seat' already exists");
        }

        SeatEntity seatEntity=seatMapper.toEntity(seat);
        seatEntity.setAuditorium(auditorium);
        seatsRepository.save(seatEntity);

        return seatMapper.toDto(seatEntity);
    }

    private AuditoriumEntity getAuditorium(int auditorium_id)throws ResourceNotFoundException {
        return auditoriumsRepository.findById(auditorium_id).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.AUDITORIUM));
    }

    public SeatDto update(int id, SeatSaveDto updatedSeat) throws ResourceNotFoundException, UniqueViolationException {
        SeatEntity seat=seatsRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.SEAT));

        AuditoriumEntity auditorium=getAuditorium(updatedSeat.getAuditorium_id());

        if(seat.getNumber()!=(updatedSeat.getNumber()) &&
        seat.getRow()!= updatedSeat.getRow() && auditorium.getId()!=(updatedSeat.getAuditorium_id())
            && seatsRepository.findByRowAndNumberAndAuditorium(seat.getRow(),seat.getNumber(),auditorium).isPresent()){
            throw new UniqueViolationException(ErrorInfo.ResourceType.SEAT, "'seat' already exists");
        }
        SeatEntity updatedSeatEntity=seatMapper.toEntity(updatedSeat);
        updatedSeatEntity.setId(id);
        updatedSeatEntity.setAuditorium(auditorium);

        seatsRepository.save(updatedSeatEntity);

        return seatMapper.toDto(updatedSeatEntity);
    }

    public void remove(int id)throws  ResourceNotFoundException {
        if (!seatsRepository.existsById(id)) {
            throw new ResourceNotFoundException(ErrorInfo.ResourceType.SEAT);
        }
        seatsRepository.deleteById(id);
    }
}
