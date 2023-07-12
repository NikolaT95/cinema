package com.NikolaTabas94rn.cinema.repository;

import com.NikolaTabas94rn.cinema.model.entity.ReservationEntity;
import com.NikolaTabas94rn.cinema.model.entity.ScreeningEntity;
import com.NikolaTabas94rn.cinema.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationsRepository extends JpaRepository<ReservationEntity, Integer> {

    Optional<ReservationEntity> findByScreeningAndUserReserved(ScreeningEntity screening, UserEntity user);

   // List<ReservationEntity> findAllByUserReserved(UserEntity user);
}
