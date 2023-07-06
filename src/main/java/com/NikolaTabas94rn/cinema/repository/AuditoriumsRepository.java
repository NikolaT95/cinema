package com.NikolaTabas94rn.cinema.repository;

import com.NikolaTabas94rn.cinema.model.entity.AuditoriumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuditoriumsRepository extends JpaRepository<AuditoriumEntity, Integer> {
    @Override
    List<AuditoriumEntity> findAll();

    Optional<AuditoriumEntity> findByName(String name);
}
