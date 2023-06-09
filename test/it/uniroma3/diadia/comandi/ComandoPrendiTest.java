package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPrendiTest {
	
	private Partita partita;
	private Attrezzo attrezzo;
	private Comando comando;
	private IO io;
	private Labirinto labirinto;
	
	
	@BeforeEach
	void setUp() throws Exception {
		 this.labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
		this.partita = new Partita(labirinto);
		this.attrezzo=new Attrezzo("osso",4);
		comando = new ComandoPrendi();
		io = (IO) new IOConsole(new Scanner(System.in));
		comando.setIo(io);
	}
	
	public boolean attrezzoPresente(String s) {
		//Set<Attrezzo> set = partita.getStanzaCorrente().getAttrezzi();
		if(partita.getStanzaCorrente().getAttrezzo(s)==null)
			return false;
		return true;
		}
	
	@Test
	void testAttrezzoPresoNonSiaNullo() throws Exception {
		comando.setParametro("osso");
		comando.esegui(partita);
		assertFalse(attrezzoPresente("osso"));
	}
	
	@Test 
	void testAttrezzoNonSiaRimastoNellaStanza() {
		assertTrue(labirinto.getStanzaCorrente().removeAttrezzo(attrezzo));
	}
	
}
