package com.testeapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.testeapi.domain.Cliente;
import com.testeapi.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	/*public List<Cliente> findAll(){
		return repository.findAll();
	}*/
	
	public Page<Cliente> findAll() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
        return new PageImpl<>(repository.findAll(), pageRequest, size);
    }
	
	public Cliente findById(Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.get();
	}
	
	public Cliente insert(Cliente cliente) {
		return repository.save(cliente);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	/*public List<Cliente> findClientByName(String nome) {
		return repository.findClientByName(nome);
	}*/
	
	public Page<Cliente> findClientByName(String nome, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
        return repository.findClientByCpf(nome.toLowerCase(), pageRequest);
    }
	
	/*public Cliente findClientByCpf(String cpf) {
		return repository.findClientByCpf(cpf);
	}*/
	
	public Page<Cliente> findClientByCpf(String cod, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "cpf");
        return repository.findClientByCpf(cod.toLowerCase(), pageRequest);
    }
	
	public Page<Cliente> search(String termo, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
        return repository.search(termo.toLowerCase(), pageRequest);
    }
}
