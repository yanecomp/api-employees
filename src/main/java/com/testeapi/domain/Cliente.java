package com.testeapi.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "CLIENTES")
public class Cliente extends AbstractEntity<Long> {
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cpf;
	
	@Column(nullable = false)
	private String dataNascimento;
	
	public Cliente() {
	}

	public Cliente(String nome, String cpf, String dataNascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Integer getIdade() {		
		Calendar dateOfBirth = new GregorianCalendar();
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNasc = null;
		
		try {
			dataNasc= sdf.parse(dataNascimento);
		} catch (Exception e) {}
		
		dateOfBirth.setTime(dataNasc);
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

		return age;
	}

}
