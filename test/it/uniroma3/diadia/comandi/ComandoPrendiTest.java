package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPrendiTest {
	
	private Partita partita;
	private Attrezzo attrezzo;
	private Comando comando;
	private IO io;
	private Labirinto labirinto;
	private String parametro;
	
	
	@BeforeEach
	void setUp() throws Exception {
		this.labirinto=new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		this.partita = new Partita(labirinto);
		this.attrezzo=new Attrezzo("osso",4);
		comando = new ComandoPrendi(parametro,io);
		io = (IO) new IOConsole();
		comando.setIo(io);
	}
	
	public boolean attrezzoPresente(String s) {
		//Set<Attrezzo> set = partita.getStanzaCorrente().getAttrezzi();
		if(partita.getStanzaCorrente().getAttrezzo(s)==null)
			return false;
		return true;
		}
	
	@Test
	void testAttrezzoPresoNonSiaNullo() {
		comando.setParametro("osso");
		comando.esegui(partita);
		assertFalse(attrezzoPresente("osso"));
	}
	
	@Test 
	void testAttrezzoNonSiaRimastoNellaStanza() {
		assertTrue(labirinto.getStanzaCorrente().removeAttrezzo(attrezzo));
	}
	
}
