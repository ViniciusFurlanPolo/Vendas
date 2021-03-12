package com.vendas.api.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vendas.api.dto.FuncionarioDto;
import com.vendas.api.entities.Funcionario;
import com.vendas.api.response.Response;
import com.vendas.api.services.FuncionarioService;
import com.vendas.api.utils.ConsistenciaException;
import com.vendas.api.utils.ConversaoUtils;

@Controller
@RequestMapping("/api/funcionarios")
@CrossOrigin
public class FuncionarioController {
	
	private static Logger log = LoggerFactory.getLogger(FuncionarioController.class);
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<FuncionarioDto>> buscarPorId(@PathVariable("id") int id){
		Response<FuncionarioDto> response = new Response<FuncionarioDto>();
		
		try {
			log.info("Controller: buscando funcionario com o id: {}", id);
			
			Optional<Funcionario> funcionario = funcionarioService.buscarPorId(id);
			
			response.setDados(ConversaoUtils.converterFuncionario(funcionario.get()));
			
			return ResponseEntity.ok(response);
		
		} catch(ConsistenciaException e) {
			log.info("Controller: Inconsistência de dados: {}", e.getMessage());
			response.adicionarErro(e.getMensagem());
        	return ResponseEntity.badRequest().body(response);
        	
		} catch(Exception e) {
			log.error("Controller: Ocorreu um erro na aplicação: {}", e.getMessage());
			response.adicionarErro("Ocorreu um erro na aplicação: {}", e.getMessage());
        	return ResponseEntity.status(500).body(response);

		}
	}
	
	@GetMapping(value = "/todos/{periodo}")
	public ResponseEntity<Response<List<FuncionarioDto>>> buscarTodosFuncionarios(@PathVariable("periodo") int periodo) {
		
		Response<List<FuncionarioDto>> response = new Response<List<FuncionarioDto>>();
		
		try {
			log.info("Controller: buscando todos os funcionarios");
			
			List<Funcionario> funcionarios = funcionarioService.buscasTodos(periodo);
			
			response.setDados(ConversaoUtils.converterListaFuncionarios(funcionarios));
			
			return ResponseEntity.ok(response);
			
		} catch (ConsistenciaException e) {

			log.info("Controller: Inconsistência de dados: {}", e.getMessage());
			response.adicionarErro(e.getMensagem());
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {

			log.error("Controller: Ocorreu um erro na aplicação: {}", e.getMessage());
			response.adicionarErro("Ocorreu um erro na aplicação: {}", e.getMessage());
			return ResponseEntity.status(500).body(response);
		}

		
	}
	

}
