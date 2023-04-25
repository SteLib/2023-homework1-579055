package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

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
		io = (IO) new IOConsole();
		fabbrica = new FabbricaDiComandiFisarmonica(io);
	
	}

	@Test
	void testComandoConParametro() {
	comandoVai =new ComandoVai("ovest", io);
	Comando corrente = fabbrica.costruisciComando("vai ovest");
	assertEquals(comandoVai.getNome(), corrente.getNome());
	assertEquals(comandoVai.getParametro(), corrente.getParametro());
	}
	
	@Test
	public void testComandoSenzaParametro2() {
		comandoFine = new ComandoFine("fine", io);
		assertEquals( comandoFine.getNome(), fabbrica.costruisciComando("fine").getNome());
	}
}
