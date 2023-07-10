package com.NikolaTabas94rn.cinema.repository;

import com.NikolaTabas94rn.cinema.model.entity.AuditoriumEntity;
import com.NikolaTabas94rn.cinema.model.entity.MovieEntity;
import com.NikolaTabas94rn.cinema.model.entity.ScreeningEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.sql.Timestamp;
import java.util.Optional;

public interface ScreeningsRepository extends JpaRepository<ScreeningEntity, Integer>, JpaSpecificationExecutor<ScreeningEntity> {

    Page<ScreeningEntity> findAllByOrderByScreeningStart(PageRequest of);

    Optional<ScreeningEntity> findByScreeningStart(Timestamp screeningStart);

    Optional<ScreeningEntity> findByScreeningStartAndAuditorium(Timestamp screeningStart, AuditoriumEntity auditorium);

    Page<ScreeningEntity> findAllByMovie(MovieEntity movie, PageRequest of);

    Page<ScreeningEntity> findByScreeningStartBetween(Timestamp startTime, Timestamp endTime, PageRequest of);
}
