package com.vendas.api.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
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
	
	
	
	public List<Funcionario> calculaMedia (InicioFimEntitie inicioFim, List<Venda> vendas, List<Funcionario> funcionarios) throws ConsistenciaException , ParseException{
		
		//List<Venda> vendas = vendaRepository.findPordataVenda(inicioFim.getInicio(), inicioFim.getFim());
		
		String[] numIni = inicioFim.getInicio().split("/");
		String[] numFim = inicioFim.getFim().split("/");
		
		int anoIni = Integer.parseInt(numIni[0]);
		int mesIni = Integer.parseInt(numIni[1]);
		int diaIni = Integer.parseInt(numIni[2]);
		int num1 = anoIni - mesIni - diaIni;
		
		int anoFim = Integer.parseInt(numFim[0]);
		int mesFim = Integer.parseInt(numFim[1]);
		int diaFim = Integer.parseInt(numFim[2]);
		int num2 = anoFim - mesFim - diaFim;
		
		int dias = num1 - num2;
		
		for(int cont = 0; cont < funcionarios.size(); cont++ ) {
			List<Venda> vendasFunc = new ArrayList<Venda>();
			int aux = 0;
			float help = 0;
			for(Venda venda : vendas ) {
				if(vendas.get(aux).getId() == funcionarios.get(cont).getId() ) {
					vendasFunc.add(venda);
					help++;
				}
				
				aux++;
			}
			
			float media = help / dias;
			
			funcionarios.get(cont).setMediaVendas(media);
			
			
		}
		

		
		
				
		return funcionarios;
	}


}
