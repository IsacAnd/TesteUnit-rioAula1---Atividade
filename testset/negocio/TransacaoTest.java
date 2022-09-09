package negocio;

import static org.junit.Assert.*;
import java.lang.Thread;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TransacaoTest {

	Transacao transacao;
	Locacao locacao1;
	Locacao locacao2;
	Locacao locacao3;
	Locacao locacao4;
	Locacao locacao5;
	Filme filme1;
	Filme filme2;
	Filme filme3;
	Filme filme4;
	Filme filme5;

	@Before
	public void setUp() throws Exception {
		locacao1 = new Locacao();
		locacao2 = new Locacao();
		locacao3 = new Locacao();
		locacao4 = new Locacao();
		locacao5 = new Locacao();
		
		
		filme1 = new Filme("Java", Genero.DRAMA);
		filme1.valorCompra = 100;
		filme1.id=10;

		filme2 = new Filme("JavaScript", Genero.ROMANCE);
		filme2.valorCompra = 50;
		filme2.id=20;
		
		filme3 = new Filme("Java e JavaScript 2", Genero.ROMANCE);
		filme3.valorCompra = 25;
		filme3.id=30;
		
		filme4 = new Filme("Java e JavaScript 3", Genero.ROMANCE);
		filme4.valorCompra = 25;
		filme4.id=4;
		
		filme5 = new Filme("Java e JavaScript 4", Genero.ROMANCE);
		filme5.valorCompra = 25;
		filme5.id=5;

		locacao1.alugar(new Cliente("Izaias", 2), filme1);
		locacao2.alugar(new Cliente("Thiago", 3), filme2);
		locacao3.alugar(new Cliente("Isac", 1), filme3);
		locacao1.valorAluguel = 50;
		locacao2.valorAluguel = 10;

		transacao = new Transacao();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test 
	public void verificaListaFilmesFavoritosTest() {

		transacao.filmesFavoritos(filme4);
		transacao.filmesFavoritos(filme5);
		assertTrue(false == transacao.favoritos.isEmpty());
	}
	
	@Test 
	public void verificacaDescontoFilmeRomance() {

		transacao.alugueis.add(locacao1);
		transacao.alugueis.add(locacao2);
		assertTrue(9 == transacao.descontoRomance(filme2));
	}
	
	@Test 
	public void verificacaodataEHora() {

		transacao.alugueis.add(locacao1);
		transacao.alugueis.add(locacao2);
		assertEquals(transacao.dataEHora(filme1), transacao.dataEHora(filme2));
	}
	
	@Test 
	public void verificacaoQualOGeneroMaisEscolhido() {

		transacao.alugueis.add(locacao1);
		transacao.alugueis.add(locacao2);
		transacao.alugueis.add(locacao3);
		assertEquals(Genero.ROMANCE, transacao.consultaGenero());
	}

	@Test
	public void valorLocacaoTotalTest() {

		transacao.alugueis.add(locacao1);
		transacao.alugueis.add(locacao2);
		assertEquals(transacao.alugueis.get(0).cliente.nome, "Izaias");
		assertTrue("Filme deveria ser selecionado corretamente",transacao.alugueis.get(1).filme.id==20);
		assertEquals(150, transacao.valorLocacaoTotal(), 0.1);
	}

	@Test
	public void valorLocacaoTotalTest2() {

		transacao.alugueis.add(locacao1);
		transacao.alugueis.add(locacao2);
		transacao.alugueis.add(locacao2);
		assertEquals(200, transacao.valorLocacaoTotal(), 0.1);
	}

	@Test 
	public void buscaClienteIdTest() {
		
		transacao.alugueis.add(locacao2);
		assertEquals("Thiago",transacao.buscaCliente(3).nome);
	}
	
	@Test
	public void buscarClientePorIdNulo() {
		transacao.alugueis.add(locacao2);
		assertTrue(transacao.buscaCliente(4)==null);	
	}
	@Test 
	public void calculoLucroTest() {
		
		locacao2.setValorAluguel(25);
		transacao.alugueis.add(locacao2);
		
		assertEquals(50,transacao.calculoLucro(20),0.01);
	}
}
