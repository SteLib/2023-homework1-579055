package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartitaTest {
	
	private Partita partitaVincente;
	private Partita nonVincente;
	
	@BeforeEach
	public void setUp() {
		this.partitaVincente = new Partita();
		this.partitaVincente.setStanzaCorrente(this.partitaVincente.getLabirinto().getStanzaVincente());
		this.nonVincente = new Partita();
		}
	
	@Test
	void testVinta1() {
		assertTrue(this.partitaVincente.vinta());
	}
	
	@Test
	void testVinta2() {
		assertFalse(this.nonVincente.vinta());
	}
	
	@Test
	void testIsFinita1() {
		assertTrue(this.partitaVincente.isFinita());
	}
	
	@Test
	void testIsFinita2() {
		this.nonVincente.getGiocatore().setCfu(1);
		assertFalse(this.nonVincente.isFinita());
	}
	
	
}

