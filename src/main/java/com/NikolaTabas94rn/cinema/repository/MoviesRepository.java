package com.NikolaTabas94rn.cinema.repository;

import com.NikolaTabas94rn.cinema.model.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MoviesRepository extends JpaRepository<MovieEntity, Integer> {
    @Override
    List<MovieEntity> findAll();

    List<MovieEntity> findAllByOrderByTitleAsc();

    Optional<MovieEntity> findByTitle(String title);

    List<MovieEntity> findByGenre(String genre);
}
