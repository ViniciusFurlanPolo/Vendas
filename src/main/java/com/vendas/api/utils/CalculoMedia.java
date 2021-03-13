package com.vendas.api.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.vendas.api.entities.Funcionario;
import com.vendas.api.entities.Venda;
import com.vendas.api.repositories.VendaRepository;

public class CalculoMedia {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	
	
	public List<Funcionario> calculaMedia (Date periodo, List<Funcionario> funcionarios) throws ConsistenciaException , ParseException{
		
		Date now = new Date();
		
		long milliNow = now.getTime();
		long milliPeriodo = periodo.getTime();
		long diff = milliNow - milliPeriodo;
		
		int diasPeriodo = (int) (diff / (1000*60*60*24));
		
		List<Venda> vendas = vendaRepository.findPordataVenda(periodo, now);
		
		for(int cont = 0; cont < funcionarios.size(); cont++ ) {
			List<Venda> vendasFunc = new ArrayList<Venda>();
			int aux = 0;
			for(Venda venda : vendas ) {
				if(vendas.get(aux).getId() == funcionarios.get(cont).getId() ) {
					vendasFunc.add(venda);
				}
				
				aux++;
			}
			
			int media = vendasFunc.size() / diasPeriodo;
			
			funcionarios.get(cont).setMediaVendas(media);
			
			
		}
		

		
		
				
		return funcionarios;
	}


}
