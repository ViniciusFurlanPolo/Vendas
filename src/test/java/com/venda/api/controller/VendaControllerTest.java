package com.venda.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendas.api.dto.VendasDto;
import com.vendas.api.entities.Funcionario;
import com.vendas.api.entities.Venda;
import com.vendas.api.services.VendasService;
import com.vendas.api.utils.ConsistenciaException;
import com.vendas.api.utils.ConversaoUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class VendaControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private VendasService vendaService;
	
	
	private Venda criarVendaTest() {
		
		Venda venda = new Venda();
		
		venda.setId(1);
		venda.setDataVenda(new Date());
		venda.setValor(79);
		venda.setNomeFunc("Eduardo Verissimo");
		venda.setFuncionario(new Funcionario());
		venda.getFuncionario().setId(1);
		
		
		return venda;
	}
	
	@Test
	public void salvarComSucessoTest() throws Exception {
		
		Venda venda = criarVendaTest();
		VendasDto objEntrada = ConversaoUtils.converterVenda(venda);
		
		String json = new ObjectMapper().writeValueAsString(objEntrada);
		
		BDDMockito.given(vendaService.salvar(Mockito.any(Venda.class)))
		.willReturn(venda);
		
		mvc.perform(MockMvcRequestBuilders.post("/api/vendas")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.dados.id").value(objEntrada.getId()))
				.andExpect(jsonPath("$.dados.valor").value(objEntrada.getValor()))
				.andExpect(jsonPath("$.dados.dataVenda").value(objEntrada.getDataVenda()))
				.andExpect(jsonPath("$.dados.nomeFunc").value(objEntrada.getNomeFunc()))
				.andExpect(jsonPath("$.erros").isEmpty());

	}
	
	@Test
	public void salvarComErroTest() throws Exception {
		Venda venda = criarVendaTest();
		VendasDto objEntrada = ConversaoUtils.converterVenda(venda);
		
		String json = new ObjectMapper().writeValueAsString(objEntrada);
		
		BDDMockito.given(vendaService.salvar(Mockito.any(Venda.class)))
			.willThrow(new ConsistenciaException("Teste Incosistencia"));
		
		mvc.perform(MockMvcRequestBuilders.post("/api/vendas")
			.content(json).contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.erros").value("Teste Incosistencia"));
		
	}

}
