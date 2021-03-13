package com.vendas.api.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendas.api.entities.Funcionario;
import com.vendas.api.entities.Venda;
import com.vendas.api.repositories.FuncionarioRepository;
import com.vendas.api.repositories.VendaRepository;
import com.vendas.api.utils.ConsistenciaException;
import com.vendas.api.utils.InicioFimEntitie;
import com.vendas.api.utils.CalculoMedia;

@Service
public class FuncionarioService {
	
	private static final Logger log = LoggerFactory.getLogger(Funcionario.class);
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private VendaRepository vendaRepository;
	
	//private CalculoMedia calculoMedia;
	
	public Optional<Funcionario> buscarPorId(int id) throws ConsistenciaException {
		log.info("Service: buscando um funcionario com o id: {}", id);
		
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		
		if(!funcionario.isPresent()) {
			log.info("Service: Nenhum funcionario com id: {} foi encontrado", id);
			throw new ConsistenciaException("Nenhum funcionario com id: {} foi encontrado", id); 
		}
		
		return funcionario;
	}
	
	public List<Funcionario> buscasTodos (InicioFimEntitie inicioFim) throws ConsistenciaException, ParseException{
		log.info("Service: Buscando todos os funcionarios");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		CalculoMedia calculo = new CalculoMedia();
		
		Date data = format.parse(inicioFim.getInicio());
		java.sql.Date dataSqlInicio = new java.sql.Date(data.getTime());
		data = format.parse(inicioFim.getFim());
		java.sql.Date dataSqlFim = new java.sql.Date(data.getTime());
		
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		
		List<Venda> vendas = vendaRepository.findBydataVendaBetween(dataSqlInicio, dataSqlFim);
		
		if(funcionarios.size() < 1) {
			log.info("Service: Nenhum funcionario encontrado");
			throw new ConsistenciaException("Nenhum funcionario encontado");
		}
		
		List<Funcionario> funcMedia = calculo.calculaMedia(inicioFim, vendas, funcionarios);
		
		return funcMedia;
	}
}
