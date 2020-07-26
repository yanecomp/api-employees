package com.testeapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeapi.domain.Status;
import com.testeapi.repositories.StatusRepository;

@Service
public class StatusService {
	
	@Autowired
	private StatusRepository statusRepository;
	
	public List<Status> findAll(){
		return statusRepository.findAll();
	}
	
	public Status findById(Long id) {
		Optional<Status> obj = statusRepository.findById(id);
		return obj.get();
	}
	
	public Status insert(Status user) {
		return statusRepository.save(user);
	}
	
	public void delete(Long id) {
		statusRepository.deleteById(id);
	}

}
