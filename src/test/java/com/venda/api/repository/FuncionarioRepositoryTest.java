package com.venda.api.repository;

import static org.junit.Assert.assertEquals;

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
import com.vendas.api.repositories.FuncionarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	private Funcionario funcionarioTest;
	
	private void criarFuncionarioTest() throws ParseException {
		
		funcionarioTest = new Funcionario();
		
		funcionarioTest.setId(1);
		funcionarioTest.setNome("Funcionario Teste");
		funcionarioTest.setTotalVendas(8);
		funcionarioTest.setMediaVendas(4);
		
	}
	
	@Before
	public void setUp() throws Exception{
		criarFuncionarioTest();
		funcionarioRepository.save(funcionarioTest);
	}
	
	@After
	public void tearDown() throws Exception{
		funcionarioRepository.deleteAll();
	}
	
	@Test
	public void testFindById() {
		Funcionario func = funcionarioRepository.findById(funcionarioTest.getId()).get();
		assertEquals(funcionarioTest.getId(), func.getId());
	}
	
	@Test
	public void testFindByNome() {
		Funcionario func = funcionarioRepository.findBynome(funcionarioTest.getNome());
		assertEquals(funcionarioTest.getNome(), func.getNome());
	}
	
	@Test
	public void testFindAll() {
		List<Funcionario> func = funcionarioRepository.findAll();
		assertEquals(funcionarioTest.getId(), func.get(0).getId());
	}

}
