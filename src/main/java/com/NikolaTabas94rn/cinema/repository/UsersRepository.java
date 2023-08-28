package com.NikolaTabas94rn.cinema.repository;

import com.NikolaTabas94rn.cinema.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserEntity, Integer> {

    @Override
    List<UserEntity> findAll();

    Optional<UserEntity> findByEmail(String email);
}
