package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {

	private Partita partita;
	private Attrezzo attrezzo;
	private IO io;
	private Comando comando;
	private Labirinto labirinto;


	@BeforeEach
	void setUp() throws Exception {
		 this.labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
		this.partita = new Partita(labirinto);
		this.attrezzo=new Attrezzo("osso",4);
		comando = new ComandoPosa();
		io = (IO) new IOConsole(new Scanner(System.in));
		comando.setIo(io);
	}

	@Test
	void testAttrezzoPosatoNellaStanza() throws Exception {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comando.setParametro("osso");
		comando.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("osso"));
	}

	@Test
	public void testAttrezzoPosatoNull() throws Exception {
		comando.setParametro("osso");
		comando.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("osso"));
	}


}
