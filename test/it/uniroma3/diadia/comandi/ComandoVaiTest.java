package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {

	private Partita partita;
	private Stanza stanzaCorrente;
	private Stanza prossimaStanza;
	private ComandoVai vai;
	private IO io;
	private Labirinto labirinto;

	@BeforeEach
	void setUp() throws Exception {
		io = new IOConsole(new Scanner(System.in));
		this.prossimaStanza=new Stanza("biblioteca");
		this.stanzaCorrente=new Stanza("atrio");
		this.labirinto= Labirinto.newBuilder("labirinto2.txt").getLabirinto();
		this.partita =new Partita(labirinto);
		vai = new ComandoVai();
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
		stanzaCorrente.impostaStanzaAdiacente(Direzione.ovest, prossimaStanza);
		vai.setParametro("sud");
		vai.esegui(partita);
		assertEquals(prossimaStanza, partita.getStanzaCorrente());
	}
	
	
	@Test
	void testDirezioneNulla() {
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(Direzione.ovest);
		assertNull(prossimaStanza);
	}

}
