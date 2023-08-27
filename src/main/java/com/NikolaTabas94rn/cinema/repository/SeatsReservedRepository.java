package com.NikolaTabas94rn.cinema.repository;

import com.NikolaTabas94rn.cinema.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatsReservedRepository extends JpaRepository<SeatReservedEntity, Integer> {

    List<SeatReservedEntity> findAllByReservation(ReservationEntity reservation);

    Optional<SeatReservedEntity> findBySeatAndScreening(SeatEntity seat, ScreeningEntity screening);
}
