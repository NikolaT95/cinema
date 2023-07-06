package com.NikolaTabas94rn.cinema.repository;

import com.NikolaTabas94rn.cinema.model.entity.AuditoriumEntity;
import com.NikolaTabas94rn.cinema.model.entity.ScreeningEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ScreeningsRepository extends JpaRepository<ScreeningEntity, Integer> {

    List<ScreeningEntity> findAllByOrderByScreeningStart();

    Optional<ScreeningEntity> findByScreeningStart(Timestamp screeningStart);

    Optional<ScreeningEntity> findByScreeningStartAndAuditorium(Timestamp screeningStart, AuditoriumEntity auditorium);

    List<ScreeningEntity> findAllByMovie(int movieId);

    List<ScreeningEntity> findByScreeningStartBetween(Timestamp startTime, Timestamp endTime);
}
