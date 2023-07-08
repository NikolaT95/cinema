package com.NikolaTabas94rn.cinema.repository;

import com.NikolaTabas94rn.cinema.model.entity.ReservationEntity;
import com.NikolaTabas94rn.cinema.model.entity.SeatReservedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatsReservedRepository extends JpaRepository<SeatReservedEntity, Integer> {

    List<SeatReservedEntity> findAllByReservation(ReservationEntity reservation);
}
