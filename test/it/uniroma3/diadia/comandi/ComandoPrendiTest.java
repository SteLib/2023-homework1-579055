package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPrendiTest {
	
	private Partita partita;
	private Attrezzo attrezzo;
	private Comando comando;
	private IO io;
	
	
	@BeforeEach
	void setUp() throws Exception {
		this.partita = new Partita();
		this.attrezzo=new Attrezzo("osso",4);
		comando = new ComandoPrendi();
		io = (IO) new IOConsole();
		comando.setIo(io);
	}

	@Test
	void testAttrezzoPresoNonSiaNullo() {
		comando.setParametro("osso");
		comando.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("martello"));
	}
	
	@Test 
	void testAttrezzoNonSiaRimastoNellaStanza() {
		assertTrue(partita.getStanzaCorrente().removeAttrezzo(attrezzo));
	}
	
}
