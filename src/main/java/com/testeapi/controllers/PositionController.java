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

import com.testeapi.domain.Position;
import com.testeapi.services.PositionService;

@RestController
@RequestMapping(value="/position")
public class PositionController {
	
	@Autowired
	PositionService positionService;
	
	@GetMapping(value="/list")
	public ResponseEntity<List<Position>> findAll(){
		List<Position> list = positionService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Position> findById(@PathVariable Long id){
		Position pos = positionService.findById(id);
		return ResponseEntity.ok().body(pos);
	}
	
	@PostMapping
	public ResponseEntity<Position> insert(@RequestBody Position pos){
		pos = positionService.insert(pos);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pos.getPositionId()).toUri();
		return ResponseEntity.created(uri).body(pos);
	}
	
	@PutMapping
    public ResponseEntity<Position> edit(@RequestBody Position pos) {
        final Position updatedPosition = positionService.insert(pos);
        return ResponseEntity.ok().body(updatedPosition);
    }
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		if(!positionService.positionHasEmployees(id)) {
			positionService.delete(id);
			
		}
		return ResponseEntity.noContent().build();
		
	}

}
