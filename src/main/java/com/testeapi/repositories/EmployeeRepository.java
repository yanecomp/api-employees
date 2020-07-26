package com.testeapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeapi.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
