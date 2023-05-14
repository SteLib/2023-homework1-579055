package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {

	private Attrezzo lanterna;
	private StanzaBuia stanza;
	
	@BeforeEach
	void setUp() throws Exception {
		 this.lanterna=new Attrezzo("lanterna", 1);
			this.stanza = new StanzaBuia("StanzaBuia", "lanterna");

	}

	@Test
	void testNonBuia() {
		stanza.addAttrezzo(lanterna);
		assertEquals(stanza.toString(), stanza.getDescrizione());
	}
	
	@Test 
	void testBuia () {
		String buio = "qui è buio pesto";
		assertEquals(buio, stanza.getDescrizione());	
		}
}
