package com.NikolaTabas94rn.cinema.repository;

import com.NikolaTabas94rn.cinema.model.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MoviesRepository extends JpaRepository<MovieEntity, Integer> {
    @Override
    List<MovieEntity> findAll();

    Page<MovieEntity> findAllByOrderByTitleAsc(PageRequest of);

    Optional<MovieEntity> findByTitle(String title);

    Page<MovieEntity> findByGenre(String genre, PageRequest of);
}
