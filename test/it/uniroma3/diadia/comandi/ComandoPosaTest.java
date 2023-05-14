package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {

	private Partita partita;
	private Attrezzo attrezzo;
	private String nomeAttrezzo;
	private IO io;
	private Comando comando;
	private Labirinto labirinto;


	@BeforeEach
	void setUp() throws Exception {
		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("seghetto", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		this.partita = new Partita(labirinto);
		this.attrezzo=new Attrezzo("osso",4);
		comando = new ComandoPosa(nomeAttrezzo, io);
		io = (IO) new IOConsole();
		comando.setIo(io);
	}

	@Test
	void testAttrezzoPosatoNellaStanza() {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comando.setParametro("osso");
		comando.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("osso"));
	}

	@Test
	public void testAttrezzoPosatoNull() {
		comando.setParametro("osso");
		comando.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("osso"));
	}


}
