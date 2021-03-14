package com.venda.api.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.sun.el.parser.ParseException;
import com.vendas.api.entities.Funcionario;
import com.vendas.api.entities.Venda;
import com.vendas.api.repositories.VendaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class VendaRepositoryTest {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	private Venda vendaTest;
	
	private void criarVendaTest() throws ParseException {
		
		Date now = new Date(); 
		
		vendaTest = new Venda();
		
		vendaTest.setId(1);
		vendaTest.setDataVenda(now);
		vendaTest.setValor(95.6);
		vendaTest.setNomeFunc("Verissimo");
		
		Funcionario func = new Funcionario();
		func.setId(2);
		
		vendaTest.setFuncionario(func);
		
	}
	
	@Before
	public void setUp() throws Exception{
		criarVendaTest();
		vendaRepository.save(vendaTest);
		
	}

	
	@After
	public void tearDown() throws Exception{
		vendaRepository.deleteAll();
	}
	
	@Test
	public void testFindById() {
		Venda venda = vendaRepository.findById(vendaTest.getId()).get();
		assertEquals(vendaTest.getId(), venda.getId());
	}
	
	@Test
	public void testFindAll() {
		List<Venda> venda = vendaRepository.findAll();
		assertEquals(vendaTest.getId(), venda.get(0).getId());
	}
}
