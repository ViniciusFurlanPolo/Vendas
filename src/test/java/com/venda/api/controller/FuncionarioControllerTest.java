package com.venda.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.vendas.api.entities.Funcionario;
import com.vendas.api.services.FuncionarioService;
import com.vendas.api.utils.InicioFimEntitie;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FuncionarioControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private FuncionarioService funcService;
	
	private Funcionario criarFuncionarioTest() {
		
		Funcionario func = new Funcionario();
		
		func.setId(1);
		func.setNome("Funcionario Teste");
		func.setTotalVendas(8);
		func.setMediaVendas(4);
		
		
		return func;
	}
	
	@Test
	private void buscarTodosFuncionariosTest() throws Exception {
		Funcionario funcionario = criarFuncionarioTest();
		
		InicioFimEntitie iniFim = new InicioFimEntitie();
		
		List<Funcionario> lstFunc = new ArrayList<>();
		lstFunc.add(funcionario);
		
		BDDMockito.given(funcService.buscasTodos(iniFim)).willReturn(lstFunc);
		
		mvc.perform(MockMvcRequestBuilders.get("/api/fucionarios/todos")
			.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
			.andExpect(jsonPath("$.dados[0].id").value(funcionario.getId()))
			.andExpect(jsonPath("$.dados[0].nome").value(funcionario.getNome()))
			.andExpect(jsonPath("$.dados[0].totalVendas").value(funcionario.getTotalVendas()))
			.andExpect(jsonPath("$.dados[0].mediaVendas").value(funcionario.getMediaVendas()))
			.andExpect(jsonPath("$.erros").isEmpty());
		
	}
	
	

}
