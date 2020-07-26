package com.testeapi.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "POSITION")
public class Position {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long positionId;
	
	@Column(nullable = false, length = 128)
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "position")
	private List<Employee> employees = new ArrayList<>();	
	
	public Position() {
	}

	public Position(Long positionId, String description) {
		super();
		this.positionId = positionId;
		this.description = description;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}

}
