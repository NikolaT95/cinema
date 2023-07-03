package com.NikolaTabas94rn.cinema.repository;

import com.NikolaTabas94rn.cinema.model.entity.ScreeningEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningsRepository extends JpaRepository<ScreeningEntity, Integer> {

}
