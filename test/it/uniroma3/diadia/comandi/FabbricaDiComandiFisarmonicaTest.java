package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

class FabbricaDiComandiFisarmonicaTest {

	private IO io;
	private Comando comandoVai;
	private FabbricaDiComandiFisarmonica fabbrica;
	private Comando comandoFine;
	
	@BeforeEach
	void setUp() throws Exception {
		io = (IO) new IOConsole(new Scanner(System.in));
		fabbrica = new FabbricaDiComandiFisarmonica(io);
	
	}

	@Test
	void testComandoConParametro() throws Exception {
	comandoVai =new ComandoVai();
	Comando corrente = fabbrica.costruisciComando("vai ovest");
	assertEquals(comandoVai.getNome(), corrente.getNome());
	assertEquals(comandoVai.getParametro(), corrente.getParametro());
	}
	
	@Test
	public void testComandoSenzaParametro2() throws Exception {
		comandoFine = new ComandoFine();
		assertEquals( comandoFine.getNome(), fabbrica.costruisciComando("fine").getNome());
	}
}
