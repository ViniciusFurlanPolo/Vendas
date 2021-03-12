package com.vendas.api.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vendas.api.dto.VendasDto;
import com.vendas.api.entities.Venda;
import com.vendas.api.response.Response;
import com.vendas.api.services.VendasService;
import com.vendas.api.utils.ConsistenciaException;
import com.vendas.api.utils.ConversaoUtils;

@Controller
@RequestMapping("/api/vendas")
@CrossOrigin
public class VendasController {
	
	private static final Logger log = LoggerFactory.getLogger(VendasController.class);
	
	@Autowired
	private VendasService vendasService;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Venda>> buscarPorId(@PathVariable("id") int id){
		
		Response<Venda> response = new Response<Venda>();
		
		try {
			log.info("Controller: buscando venda com id: {}", id);
			
			Optional<Venda> venda = vendasService.buscarPorId(id);
			
			response.setDados(venda.get());
			
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
	
	@PostMapping()
	public ResponseEntity<Response<VendasDto>> salvar(@RequestBody VendasDto vendaDto) {
		
		Response<VendasDto> response = new Response<VendasDto>();
		
		try {
			log.info("Controller: salvando a venda: {}", vendaDto.toString());
			
			Venda venda = this.vendasService.salvar(ConversaoUtils.converterVendaDto(vendaDto));
			
			response.setDados(ConversaoUtils.converterVenda(venda));
			
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
