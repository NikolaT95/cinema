package com.NikolaTabas94rn.cinema.service;

import com.NikolaTabas94rn.cinema.exceptions.BadRequestException;
import com.NikolaTabas94rn.cinema.exceptions.ErrorInfo;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.model.api.reservation.ReservationDto;
import com.NikolaTabas94rn.cinema.model.api.reservation.ReservationSaveDto;
import com.NikolaTabas94rn.cinema.model.api.seatReserved.SeatReservedDto;
import com.NikolaTabas94rn.cinema.model.entity.*;
import com.NikolaTabas94rn.cinema.model.mapper.ReservationMapper;
import com.NikolaTabas94rn.cinema.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationsRepository reservationsRepository;
    private final UsersRepository usersRepository;
    private final ScreeningsRepository screeningsRepository;
    private final SeatsRepository seatsRepository;
    private final SeatsReservedRepository seatsReservedRepository;

    private final ReservationMapper reservationMapper;

    public List<ReservationDto> getUsersReservations(int userId) throws ResourceNotFoundException {
        UserEntity user=getUser(userId);

        return user.getReservations()
                .stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ReservationDto> getScreeningsReservations(int screeningId) throws ResourceNotFoundException {
        ScreeningEntity screening=getScreening(screeningId);

        return screening.getReservations()
                .stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }

    public ReservationDto getScreeningForUser(int userId,int screeningId) throws ResourceNotFoundException {
        UserEntity user=getUser(userId);
        ScreeningEntity screening=getScreening(screeningId);
        ReservationEntity reservationEntity=reservationsRepository.findByScreeningAndUserReserved(screening,user).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.RESERVATION));

        return reservationMapper.toDto(reservationEntity);
    }

    public ReservationDto save(int screeningId, int userId, ReservationSaveDto reservationSaveDto) throws ResourceNotFoundException, BadRequestException {
        UserEntity user=getUser(userId);
        ScreeningEntity screening=getScreening(screeningId);

        if(reservationsRepository.findByScreeningAndUserReserved(screening,user).isPresent()) {
            throw new BadRequestException("User with id " + userId + " has already made reservation for screening with id " + screeningId);
        }

        ReservationEntity reservationEntity=reservationMapper.toEntity(reservationSaveDto);
        reservationEntity.setUserReserved(user);
        reservationEntity.setScreening(screening);
        reservationEntity = reservationsRepository.save(reservationEntity);



        for(SeatReservedDto seatReservedDto: reservationSaveDto.getReservedSeats()){
            SeatReservedEntity seatReservedEntity=new SeatReservedEntity();
            SeatEntity seat=getSeat(seatReservedDto.getSeat_id());
            seatReservedEntity.setSeat(seat);
            seatReservedEntity.setReservation(reservationEntity);
            seatReservedEntity.setScreening(screening);

            seatsReservedRepository.save(seatReservedEntity);
        }


        return reservationMapper.toDto(reservationEntity);
    }

    public void remove(int screeningId, int userId) throws ResourceNotFoundException {
        UserEntity user=getUser(userId);
        ScreeningEntity screening=getScreening(screeningId);

        ReservationEntity reservationEntity=reservationsRepository.findByScreeningAndUserReserved(screening,user).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.RESERVATION));
        List<SeatReservedEntity> seatReservedEntities=seatsReservedRepository.findAllByReservation(reservationEntity);
        for (SeatReservedEntity seatReserved: seatReservedEntities
             ) {
            seatsReservedRepository.delete(seatReserved);
        }
        reservationsRepository.delete(reservationEntity);

    }

    private SeatEntity getSeat(int seat_id) throws ResourceNotFoundException {
        return seatsRepository.findById(seat_id).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.SEAT));
    }


    private ScreeningEntity getScreening(int screeningId) throws ResourceNotFoundException {
        return screeningsRepository.findById(screeningId).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.SCREENING));
    }


    private UserEntity getUser(int userId) throws ResourceNotFoundException {
        return usersRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.USER));
    }



}
