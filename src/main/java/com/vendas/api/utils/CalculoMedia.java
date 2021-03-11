package com.vendas.api.utils;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vendas.api.entities.Funcionario;
import com.vendas.api.repositories.VendaRepository;

public class CalculoMedia {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	public Optional<List<Funcionario>> calculaMedia (int periodo, Optional<List<Funcionario>> funcionarios) throws ConsistenciaException , ParseException{
		
		
		
		return null;
	}

}
