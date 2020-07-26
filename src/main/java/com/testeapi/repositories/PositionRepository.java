package com.testeapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeapi.domain.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

}
