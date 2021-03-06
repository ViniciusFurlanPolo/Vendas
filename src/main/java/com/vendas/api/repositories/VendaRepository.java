package com.vendas.api.repositories;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.vendas.api.entities.Venda;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
	
	@Transactional(readOnly = true)
	@Query("SELECT fc FROM Venda fc WHERE fc.funcionario.id = :idFunc")
	List<Venda> findByid_func(int idFunc);
	
	
	List<Venda> findBydataVendaBetween(Date inicio, Date fim);
}
