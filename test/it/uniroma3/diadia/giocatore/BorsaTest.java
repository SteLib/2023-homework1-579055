package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	private Borsa vuota;
	private Borsa nonVuota;
	private Attrezzo osso;

	@BeforeEach
	public void setUp() {
		this.vuota = new Borsa();
		this.nonVuota= new Borsa();
		this.osso= new Attrezzo("osso", 1);
		this.nonVuota.addAttrezzo(osso);
	}

	@Test
	void testAddAttrezzo() {
		this.vuota.addAttrezzo(osso);
		assertTrue(this.vuota.hasAttrezzo("osso"));
	}

	@Test
	void testIsEmpty() {
		assertTrue(this.vuota.isEmpty());
	}

	@Test
	void testHasAttrezzo() {
		assertFalse(this.vuota.hasAttrezzo("osso"));
	}

	@Test
	void testRemoveAttrezzo1() {
		this.nonVuota.removeAttrezzo("osso");
		assertFalse(this.nonVuota.hasAttrezzo("osso"));
	}


	@Test
	void testRemoveAttrezzo2() {
		assertNull(this.vuota.removeAttrezzo("osso"));
	}
	
	@Test
	void testRemoveAttrezzo3() {
		assertEquals(osso, this.nonVuota.removeAttrezzo("osso"));
	}
}
	
