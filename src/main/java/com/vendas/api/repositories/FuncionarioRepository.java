package com.vendas.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.vendas.api.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
	
	@Transactional(readOnly = true)
	Funcionario findBynome(String nome);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Funcionario SET totalVendas = :novoTotal WHERE id = :id")
	void alteraTotalVendas(@Param("novoTotal") int totalVendas, @Param("id") int id);
	
	

}
