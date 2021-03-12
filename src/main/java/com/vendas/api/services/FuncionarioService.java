package com.vendas.api.services;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendas.api.entities.Funcionario;
import com.vendas.api.repositories.FuncionarioRepository;
import com.vendas.api.repositories.VendaRepository;
import com.vendas.api.utils.ConsistenciaException;
import com.vendas.api.utils.CalculoMedia;

@Service
public class FuncionarioService {
	
	private static final Logger log = LoggerFactory.getLogger(Funcionario.class);
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	private CalculoMedia calculoMedia;
	
	public Optional<Funcionario> buscarPorId(int id) throws ConsistenciaException {
		log.info("Service: buscando um funcionario com o id: {}", id);
		
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		
		if(!funcionario.isPresent()) {
			log.info("Service: Nenhum funcionario com id: {} foi encontrado", id);
			throw new ConsistenciaException("Nenhum funcionario com id: {} foi encontrado", id); 
		}
		
		return funcionario;
	}
	
	public Optional<List<Funcionario>> buscasTodos (int periodo) throws ConsistenciaException, ParseException{
		log.info("Service: Buscando todos os funcionarios");
		
		Optional<List<Funcionario>> funcionarios = Optional.ofNullable(funcionarioRepository.findAll());
		
		if(!funcionarios.isPresent() || funcionarios.get().size() < 1) {
			log.info("Service: Nenhum funcionario encontrado");
			throw new ConsistenciaException("Nenhum funcionario encontado");
		}
		
		Optional<List<Funcionario>> funcMedia = calculoMedia.calculaMedia(periodo, funcionarios);
		
		return funcMedia;
	}
}
