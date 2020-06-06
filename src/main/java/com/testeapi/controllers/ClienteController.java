package com.testeapi.controllers;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.testeapi.domain.Cliente;
import com.testeapi.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	ClienteService service;
	
	@GetMapping("/listar")
    public Page<Cliente> findAll() {
        return service.findAll();
    }
	
	@PostMapping
	public ResponseEntity<Cliente> insert(@RequestBody Cliente obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@GetMapping("/nome")
    public Page<Cliente> findByName(
            @RequestParam("name") String name,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return service.findClientByCpf(name, page, size);

    }
	
	@GetMapping("/cpf")
    public Page<Cliente> findByCpf(
            @RequestParam("cod") String cod,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return service.findClientByCpf(cod, page, size);

    }
	
	@GetMapping("/search")
    public Page<Cliente> search(
            @RequestParam("termo") String termo,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return service.search(termo, page, size);

    }
	
	@PutMapping
    public ResponseEntity<Cliente> edit(@RequestBody Cliente cliente) {
        final Cliente updatedCliente = service.insert(cliente);
        return ResponseEntity.ok().body(updatedCliente);
    }
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Cliente> updateCpf(@PathVariable Long id, @RequestBody Map<Object, Object> fields) {
		Cliente cliente = service.findById(id);
	    
		fields.forEach((k, v) -> {
	        Field field = ReflectionUtils.findRequiredField(Cliente.class, (String) k);
	        ReflectionUtils.setField(field, cliente, v);
	    });
		
		final Cliente updatedCliente = service.insert(cliente);
		
		return ResponseEntity.ok().body(updatedCliente);
	}

}
