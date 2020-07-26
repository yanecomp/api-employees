package com.testeapi.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.testeapi.domain.Employee;
import com.testeapi.services.EmployeeService;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping(value="/list")
	public ResponseEntity<List<Employee>> findAll(){
		List<Employee> list = employeeService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Employee> findById(@PathVariable Long id){
		Employee user = employeeService.findById(id);
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping
	public ResponseEntity<Employee> insert(@RequestBody Employee emp){
		emp = employeeService.insert(emp);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(emp.getEmployeeId()).toUri();
		return ResponseEntity.created(uri).body(emp);
	}
	
	@PutMapping
    public ResponseEntity<Employee> edit(@RequestBody Employee emp) {
        final Employee updatedCliente = employeeService.update(emp);
        return ResponseEntity.ok().body(updatedCliente);
    }
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		employeeService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
