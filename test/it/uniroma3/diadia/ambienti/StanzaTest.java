package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	private Stanza vuota;
	private Stanza nonVuota;
	private Attrezzo osso;

	
	@BeforeEach
	public void setUp() {
		this.vuota = new Stanza("vuota");
		this.nonVuota= new Stanza("non-vuota");
		this.osso= new Attrezzo("osso", 1);
		this.nonVuota.addAttrezzo(osso);
	}
	
	@Test
	void testImpostaStanzaAdiacente() {
		this.nonVuota.impostaStanzaAdiacente("nord", vuota);
		assertSame(this.vuota, this.nonVuota.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testaddAttrezzo1() {
		this.vuota.addAttrezzo(new Attrezzo("osso" , 1));
		assertTrue(this.vuota.hasAttrezzo("osso"));
	}
	
	@Test 
	void testHasAttrezzoStanzaVuota() {
		assertFalse(this.vuota.hasAttrezzo("osso"));
	}
	
	@Test 
	final void testGetAttrezzoStanzaNonVuota() {
		assertSame(this.osso,this.nonVuota.getAttrezzo("osso"));
	}
	
	@Test 
	final void testGetAttrezzoStanzaVuota() {
		assertNull(this.vuota.getAttrezzo("osso"));
	}
	
	@Test
	final void testRemoveAttrezzoStanzaPiena() {
		assertTrue(this.nonVuota.removeAttrezzo(this.osso));
		
	}
	
	@Test
	final void testRemoveAttrezzoStanzaVuota() {
		assertFalse(this.vuota.removeAttrezzo(this.osso));
	}
	
	
}
