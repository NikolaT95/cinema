package com.NikolaTabas94rn.cinema.service;

import com.NikolaTabas94rn.cinema.exceptions.ErrorInfo;
import com.NikolaTabas94rn.cinema.exceptions.ResourceNotFoundException;
import com.NikolaTabas94rn.cinema.model.api.reservation.ReservationDto;
import com.NikolaTabas94rn.cinema.model.api.seatReserved.SeatReservedDto;
import com.NikolaTabas94rn.cinema.model.entity.ReservationEntity;
import com.NikolaTabas94rn.cinema.model.entity.ScreeningEntity;
import com.NikolaTabas94rn.cinema.model.mapper.SeatReservedMapper;
import com.NikolaTabas94rn.cinema.repository.ReservationsRepository;
import com.NikolaTabas94rn.cinema.repository.ScreeningsRepository;
import com.NikolaTabas94rn.cinema.repository.SeatsReservedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatReservedService {
    private final SeatsReservedRepository seatsReservedRepository;
    private final ReservationsRepository reservationsRepository;
    private final ScreeningsRepository screeningsRepository;
    private final SeatReservedMapper seatReservedMapper;


    public List<SeatReservedDto> getReservationReservedSeats(int reservationId) throws ResourceNotFoundException {
        ReservationEntity reservationEntity=getReservation(reservationId);

        return reservationEntity.getReservedSeats()
                .stream()
                .map(seatReservedMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<SeatReservedDto> getScreeningReservedSeats(int screeningId) throws ResourceNotFoundException {
        ScreeningEntity screeningEntity=getScreening(screeningId);

        return screeningEntity.getReservedSeats()
                .stream()
                .map(seatReservedMapper::toDto)
                .collect(Collectors.toList());
    }

    private ScreeningEntity getScreening(int screeningId) throws ResourceNotFoundException {
        return screeningsRepository.findById(screeningId).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.SCREENING));
    }

    private ReservationEntity getReservation(int reservationId) throws ResourceNotFoundException {
        return reservationsRepository.findById(reservationId).orElseThrow(()->new ResourceNotFoundException(ErrorInfo.ResourceType.RESERVATION));
    }
}
