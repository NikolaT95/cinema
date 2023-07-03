package com.NikolaTabas94rn.cinema.repository;

import com.NikolaTabas94rn.cinema.model.entity.SeatReservedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatsReservedRepository extends JpaRepository<SeatReservedEntity, Integer> {
}
