package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {
	
	private Partita partita;
	private Partita nonVincente;
	private Labirinto labirinto;
	private Stanza stanza;
	
	@BeforeEach
	public void setUp() {
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		this.partita = new Partita(labirinto);
		this.labirinto.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		this.nonVincente = new Partita(labirinto);
		this.stanza = new Stanza("Stanza");
		}
	
	@Test
	void testVinta1() {
		assertEquals("Biblioteca", this.partita.getLabirinto().getStanzaVincente().getNome());
	}
	
	@Test
	void testVinta2() {
		assertFalse(this.nonVincente.vinta());
	}
	
	@Test
	public void testSetStanzaCorrente() {
		partita.getLabirinto().setStanzaCorrente(this.stanza);
		assertEquals(this.stanza, partita.getLabirinto().getStanzaCorrente());
	}
	
	@Test
	void testIsFinita2() {
		this.nonVincente.getGiocatore().setCfu(1);
		assertFalse(this.nonVincente.isFinita());
	}
	
	
}

