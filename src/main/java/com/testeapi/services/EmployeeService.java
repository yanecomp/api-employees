package com.testeapi.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeapi.domain.Employee;
import com.testeapi.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	public Employee findById(Long id) {
		Optional<Employee> obj = employeeRepository.findById(id);
		return obj.get();
	}
	
	public Employee insert(Employee user) {
		LocalDate localDate = LocalDate.now();
		user.setCreated(localDate);
		return employeeRepository.save(user);
	}
	
	public Employee update(Employee user) {
		LocalDate localDate = LocalDate.now();
		user.setUpdated(localDate);
		return employeeRepository.save(user);
	}
	
	public void delete(Long id) {
		employeeRepository.deleteById(id);
	}

}
