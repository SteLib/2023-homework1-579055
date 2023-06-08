package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class LabirintoBuilderTest {
	Labirinto.LabirintoBuilder labirinto;
	
	@BeforeEach
	void setUp() throws Exception {
		labirinto = new LabirintoBuilder("labirinto3.txt");
	}

	@Test
	public void testGetLabirinto() {
		assertNotNull(labirinto.getLabirinto());
		assertEquals(Labirinto.class, labirinto.getLabirinto().getClass());
	}

	@Test
	public void testAddAttrezzoSenzaUltimaStanzaAggiunta(){
		assertEquals(LabirintoBuilder.class, labirinto.addAttrezzo("estintore", 3).getClass());
	}
	
	@Test
	public void testAddAttrezzoConUltimaStanzaAggiunta(){
		labirinto.addStanzaIniziale("N11").addAttrezzo("cacciavite", 3);
		Attrezzo atteso = new Attrezzo("estintore", 3);
		assertEquals(atteso, labirinto.getLabirinto().getStanzaCorrente().getAttrezzo("estintore"));		
	}

	@Test
    public void testAddAttrezzoConStanza() {
        labirinto.addStanza("N11");
        labirinto.addAttrezzo("estintore", 3);
        assertTrue(labirinto.getNome2stanza().get("N11").hasAttrezzo("estintore"));
    }

}

