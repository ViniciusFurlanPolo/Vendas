package com.vendas.api.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.vendas.api.dto.FuncionarioDto;
import com.vendas.api.entities.Funcionario;

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

}
