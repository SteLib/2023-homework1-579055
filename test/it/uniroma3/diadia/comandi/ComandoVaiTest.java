package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {

	private Partita partita;
	private Stanza stanzaCorrente;
	private Stanza prossimaStanza;
	private String direzione;
	private ComandoVai vai;
	private IO io;
	private Labirinto labirinto;

	@BeforeEach
	void setUp() throws Exception {
		io = new IOConsole();
		this.prossimaStanza=new Stanza("biblioteca");
		this.stanzaCorrente=new Stanza("atrio");
		this.labirinto=new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("seghetto", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		this.partita =new Partita(labirinto);
		vai = new ComandoVai(direzione, io);
		vai.setIo(io);
		}

	@Test
	void testProssimaStanzaNull() {
		partita.setStanzaCorrente(stanzaCorrente);
		vai.esegui(partita);
		assertEquals(stanzaCorrente,partita.getStanzaCorrente());
	}

	@Test
	void testCambioVersoStanzaEsistente() {
		partita.setStanzaCorrente(stanzaCorrente);
		stanzaCorrente.impostaStanzaAdiacente("ovest", prossimaStanza);
		vai.setParametro("ovest");
		vai.esegui(partita);
		assertEquals(prossimaStanza, partita.getStanzaCorrente());
	}
	
	
	@Test
	void testDirezioneNulla() {
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		assertNull(prossimaStanza);
	}

}
