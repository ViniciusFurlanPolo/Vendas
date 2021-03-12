package com.vendas.api.services;

import java.text.ParseException;
import java.util.Date;
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

@Service
public class VendasService {
	
	private static final Logger log = LoggerFactory.getLogger(VendasService.class);
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public Optional<Venda> buscarPorId(int id) throws ConsistenciaException {
		log.info("Service: buscando uma venda de id: {}", id);
		
		Optional<Venda> venda = vendaRepository.findById(id);
		
		if(!venda.isPresent()) {
			log.info("Service: Nenhuma venda com id: {} foi encontrado", id);
			throw new ConsistenciaException("Nenhuma venda com id: {} foi encontrado", id);
		}
		
		return null;
	}
	
	public Venda salvar(Venda venda) throws ConsistenciaException, ParseException{
		log.info("Service: salvando a Venda: {}", venda);
		
		Date now = new Date();
		
		Funcionario func = funcionarioRepository.findBynome(venda.getFuncionario().getNome());
		int aux = func.getTotalVendas();
		func.setTotalVendas(aux + 1);
		
		venda.setDataVenda(now);
		venda.setId(func.getId());
		
		if(venda.getId() > 0)
			buscarPorId(venda.getId());
		
		funcionarioRepository.alteraTotalVendas(func.getTotalVendas(), venda.getFuncionario().getId());
		
		return vendaRepository.save(venda);
	}


}
