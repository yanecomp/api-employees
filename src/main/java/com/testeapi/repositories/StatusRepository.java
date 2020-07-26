package com.testeapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeapi.domain.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
