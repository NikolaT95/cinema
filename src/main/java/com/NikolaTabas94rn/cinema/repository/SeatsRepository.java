package com.NikolaTabas94rn.cinema.repository;

import com.NikolaTabas94rn.cinema.model.entity.AuditoriumEntity;
import com.NikolaTabas94rn.cinema.model.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatsRepository extends JpaRepository<SeatEntity, Integer> {

    Optional<SeatEntity> findByRowAndNumberAndAuditorium(Integer row, Integer seat, AuditoriumEntity auditorium);
}
