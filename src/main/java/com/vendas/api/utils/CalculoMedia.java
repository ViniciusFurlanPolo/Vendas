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
	
	
	@SuppressWarnings("deprecation")
	public List<Funcionario> calculaMedia (int periodo, List<Funcionario> funcionarios) throws ConsistenciaException , ParseException{
		
		Date now = new Date();
		
		long milliNow = now.getTime();
		
		List<Long> datas = new ArrayList<Long>();
		List<String> datasString = new ArrayList<String>();
		
		for(int cont = 0; cont < funcionarios.size(); cont++) {
			int media = 0;
			int aux2 = 0;
			for(int cont2 = 1; cont < periodo; cont++) {
				datas.add(milliNow - (86400000 * cont2));
				datasString.add(String.valueOf(datas.get(cont2 - 1)));
				Date data = new Date(datasString.get(cont2));
				
				List<Venda> vendas = vendaRepository.findBydataVenda(data);
				int aux = 0;
				for(int cont3 = 0; cont < vendas.size(); cont++) {
					if(funcionarios.get(cont).getId() == vendas.get(cont2).getFuncionario().getId()) {
						aux++;
					}
				}
				media = media + aux;
				aux2++;
				
			}
			media = media / aux2;
			
			funcionarios.get(cont).setMediaVendas(media);
			
			
		}
		
		
		
		
		for(int cont = 0; cont < datas.size(); cont++) {
			datasString.add(String.valueOf(datas.get(cont)));
		}
				
		return funcionarios;
	}


}
