package com.vendas.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.vendas.api.entities.Funcionario;
import com.vendas.api.repositories.FuncionarioRepository;
import com.vendas.api.utils.ConsistenciaException;
import com.vendas.api.utils.InicioFimEntitie;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioServiceTest {
	
	@MockBean
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Test
	public void buscarPorIdTest() throws ConsistenciaException {
		BDDMockito.given(funcionarioRepository.findById(Mockito.anyInt())).willReturn(Optional.of(new Funcionario()));
		
		Optional<Funcionario> resultado = funcionarioService.buscarPorId(1);
		
		assertTrue(resultado.isPresent());
		
	}
	
	@Test
	public void buscarTodosTest() throws ConsistenciaException, ParseException {
		
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios.add(new Funcionario());
		
		InicioFimEntitie inFi = new InicioFimEntitie();
		
		BDDMockito.given(funcionarioRepository.findAll()).willReturn(funcionarios);
		
		Optional<List<Funcionario>> resultado = Optional.ofNullable(funcionarioService.buscasTodos(inFi));
		
		assertTrue(resultado.get().size() > 0);
	}

}
