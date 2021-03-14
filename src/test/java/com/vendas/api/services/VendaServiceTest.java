package com.vendas.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
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

import com.vendas.api.entities.Venda;
import com.vendas.api.repositories.VendaRepository;
import com.vendas.api.utils.ConsistenciaException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class VendaServiceTest {
	
	@MockBean
	private VendaRepository vendaRepository;
	
	@Autowired
	private VendasService vendaService;
	
	@Test
	public void buscarPorIdTest() throws ConsistenciaException {
		BDDMockito.given(vendaRepository.findById(Mockito.anyInt())).willReturn(Optional.of(new Venda()));
		
		Optional<Venda> resultado = vendaService.buscarPorId(1);
		
		assertTrue(resultado.isPresent());
	}
	
	@Test(expected = ConsistenciaException.class)
	public void buscarPorIdNaoExistencia() throws ConsistenciaException {
		BDDMockito.given(vendaRepository.findById(Mockito.anyInt())).willReturn(Optional.empty());
		
		vendaService.buscarPorId(1);
	}
	
	@Test
	public void salvarComSucessoTest() throws ConsistenciaException, ParseException{
		
		BDDMockito.given(vendaRepository.save(Mockito.any(Venda.class))).willReturn(new Venda());
		
		Venda resultado = vendaService.salvar(new Venda());
		
		assertNotNull(resultado);
	}

}
