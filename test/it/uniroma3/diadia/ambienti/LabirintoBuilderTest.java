package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class LabirintoBuilderTest {
	LabirintoBuilder labirinto;
	
	@BeforeEach
	void setUp() throws Exception {
		labirinto = new LabirintoBuilder();
	}

	@Test
	public void testGetLabirinto() {
		assertNotNull(labirinto.getLabirinto());
		assertEquals(Labirinto.class, labirinto.getLabirinto().getClass());
	}

	@Test
	public void testAddAttrezzoSenzaUltimaStanzaAggiunta(){
		assertEquals(LabirintoBuilder.class, labirinto.addAttrezzo("cacciavite", 3).getClass());
	}
	
	@Test
	public void testAddAttrezzoConUltimaStanzaAggiunta(){
		labirinto.addStanzaIniziale("stanzetta").addAttrezzo("cacciavite", 3);
		Attrezzo atteso = new Attrezzo("cacciavite", 3);
		assertEquals(atteso, labirinto.getLabirinto().getStanzaCorrente().getAttrezzo("cacciavite"));		
	}

	@Test
    public void testAddAttrezzoConStanza() {
        labirinto.addStanza("stanzetta");
        labirinto.addAttrezzo("cacciavite", 3);
        assertTrue(labirinto.getStanzaPerNome().get("stanzetta").hasAttrezzo("cacciavite"));
    }

}

