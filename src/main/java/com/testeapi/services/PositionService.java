package com.testeapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeapi.domain.Position;
import com.testeapi.repositories.PositionRepository;

@Service
public class PositionService {
	
	@Autowired
	private PositionRepository positionRepository;
	
	public List<Position> findAll(){
		return positionRepository.findAll();
	}
	
	public Position findById(Long id) {
		Optional<Position> obj = positionRepository.findById(id);
		return obj.get();
	}
	
	public Position insert(Position user) {
		return positionRepository.save(user);
	}
	
	public void delete(Long id) {
		positionRepository.deleteById(id);
	}
	
	public boolean positionHasEmployees(Long id) {
		if(findById(id).getEmployees().isEmpty()) {
			return false;
		}
		return true;
	}

}
