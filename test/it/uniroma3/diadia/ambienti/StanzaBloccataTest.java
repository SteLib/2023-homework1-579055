package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	private StanzaBloccata stanzaLock;
	private Stanza stanzaNormale;
	private Attrezzo nomeAttrezzoSblocca;
	
	@BeforeEach
	void setUp() throws Exception {
		this.stanzaLock = new StanzaBloccata("StanzaBloccata", "nord" ,"passpartout");
		this.nomeAttrezzoSblocca=new Attrezzo("passpartout", 1);
		this.stanzaNormale=new Stanza("camera");
		stanzaLock.impostaStanzaAdiacente("ovest", stanzaNormale);
		
	}

	@Test
	void testDirezioneBlocataMaAttrezzoSblocca() {
		stanzaLock.addAttrezzo(nomeAttrezzoSblocca);
		assertEquals( stanzaNormale, stanzaLock.getStanzaAdiacente("nord"));
	}

}
