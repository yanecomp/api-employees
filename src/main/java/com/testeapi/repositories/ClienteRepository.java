package com.testeapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.testeapi.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Query("SELECT c FROM Cliente c WHERE UPPER(c.nome) LIKE CONCAT('%',UPPER(:nome),'%') ")
	//public List<Cliente> findClientByName(String username);
	public Page<Cliente> findClientByName(@Param("nome") String cod, Pageable pageable);
	
	@Query("SELECT c FROM Cliente c WHERE c.cpf LIKE %:cod%")
	//public Cliente findClientByCpf(String cod);
	public Page<Cliente> findClientByCpf(@Param("cod") String cod, Pageable pageable);
	
	@Query("SELECT c FROM Cliente c WHERE c.cpf LIKE %:termo% " +
	           "OR UPPER(c.nome) LIKE %:termo%")
	public Page<Cliente> search(@Param("termo") String termo, Pageable pageable);

}
