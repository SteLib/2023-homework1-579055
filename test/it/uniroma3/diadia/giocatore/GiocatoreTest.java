package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiocatoreTest {
	private Giocatore giocatore;

	@BeforeEach
	public void setUp() {
		giocatore = new Giocatore();
	}

	@Test
	final void testGetCfuInizio1() {
		assertEquals(20,giocatore.getCfu());
	}

	@Test 
	final void testsetCfu() {
		giocatore.setCfu(1);
		assertEquals(1, this.giocatore.getCfu());
	}
}
