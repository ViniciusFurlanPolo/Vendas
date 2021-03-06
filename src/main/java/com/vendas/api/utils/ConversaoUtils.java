package com.vendas.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vendas.api.dto.FuncionarioDto;
import com.vendas.api.dto.VendasDto;
import com.vendas.api.entities.Funcionario;
import com.vendas.api.entities.Venda;

public class ConversaoUtils {
	
	public static Funcionario converterFuncionarioDto(FuncionarioDto funcionarioDto) throws ParseException{
		
		Funcionario funcionario = new Funcionario();
		
		if(funcionarioDto.getId() != null && funcionarioDto.getId() !="") {
			funcionario.setId(Integer.parseInt(funcionarioDto.getId()));
		}
		
		funcionario.setNome(funcionarioDto.getNome());
		funcionario.setTotalVendas(Integer.parseInt(funcionarioDto.getTotalVendas()));
		funcionario.setMediaVendas(Integer.parseInt(funcionarioDto.getMediaVendas()));
		
		
		return funcionario;
		
	}
	
	public static FuncionarioDto converterFuncionario(Funcionario funcionario) {
		
		FuncionarioDto funcionarioDto = new FuncionarioDto();
		
		funcionarioDto.setId(String.valueOf(funcionario.getId()));
		funcionarioDto.setNome(funcionario.getNome());
		funcionarioDto.setTotalVendas(String.valueOf(funcionario.getTotalVendas()));
		funcionarioDto.setMediaVendas(String.valueOf(funcionario.getMediaVendas()));
		
		
		return funcionarioDto;
	}
	
	public static List<FuncionarioDto> converterListaFuncionarios(List<Funcionario> funcionarios) {
		
		List<FuncionarioDto> funcionariosDto = new ArrayList<FuncionarioDto>();
		
		for(Funcionario funcionario : funcionarios) {
			funcionariosDto.add(converterFuncionario(funcionario));
		}
		
		return funcionariosDto;
	}
	
	public static Venda converterVendaDto(VendasDto vendaDto) {
		
		Venda venda = new Venda();
		
		if(vendaDto.getId() != null && vendaDto.getId() !="")
			venda.setId(Integer.parseInt(vendaDto.getId()));
		
		venda.setValor(Double.parseDouble(vendaDto.getValor()));
		
		venda.setNomeFunc(vendaDto.getNomeFunc());
		
		/*Funcionario func = new Funcionario();
		func.setNome(vendaDto.getNomeFunc());
		
		venda.setFuncionario(func);*/
		
		
		return venda;
	}
	
	public static VendasDto converterVenda(Venda venda) {
		
		VendasDto vendaDto = new VendasDto();
		
		vendaDto.setId(Integer.toString(venda.getId()));
		vendaDto.setNomeFunc(venda.getFuncionario().getNome());
		vendaDto.setValor(Double.toString(venda.getValor()));
		vendaDto.setDataVenda(venda.getDataVenda().toString());
		
		return vendaDto;
	}
	
	/*public static InicioFimEntitie converterDataString(InicioFimEntitie inicioFim) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		
		
		
		
		
		return data;
	}*/

}
