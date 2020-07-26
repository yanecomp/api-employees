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
@Table(name = "STATUS")
public class Status {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long statusId;
	
	@Column(nullable = false, length = 128)
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "status")
	private List<Employee> employees = new ArrayList<>();	
	
	public Status() {
	}

	public Status(Long statusId, String description) {
		this.statusId = statusId;
		this.description = description;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
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
