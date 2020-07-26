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

import com.testeapi.domain.Status;
import com.testeapi.services.StatusService;

@RestController
@RequestMapping(value="/status")
public class StatusController {
	
	@Autowired
	StatusService statusService;
	
	@GetMapping(value="/list")
	public ResponseEntity<List<Status>> findAll(){
		List<Status> list = statusService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Status> findById(@PathVariable Long id){
		Status pos = statusService.findById(id);
		return ResponseEntity.ok().body(pos);
	}
	
	@PostMapping
	public ResponseEntity<Status> insert(@RequestBody Status pos){
		pos = statusService.insert(pos);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pos.getStatusId()).toUri();
		return ResponseEntity.created(uri).body(pos);
	}
	
	@PutMapping
    public ResponseEntity<Status> edit(@RequestBody Status pos) {
        final Status updatedStatus = statusService.insert(pos);
        return ResponseEntity.ok().body(updatedStatus);
    }
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		statusService.delete(id);
		return ResponseEntity.noContent().build();
		
	}

}
