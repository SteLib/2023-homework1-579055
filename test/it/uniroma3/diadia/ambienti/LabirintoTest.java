package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LabirintoTest {
	private Labirinto labirinto;
	
	@BeforeEach
	public void setUp() {
		this.labirinto = new Labirinto();
		this.labirinto.creaStanze();
	}
	
	@Test
	void testcreaStanze1() {
		assertEquals("osso", this.labirinto.getStanzaIngresso().getAttrezzo("osso").getNome());
	}
	
	@Test
	void testcreaStanze2() {
		assertEquals("lanterna", this.labirinto.getStanzaIngresso().getStanzaAdiacente("sud").getAttrezzo("lanterna").getNome());

	}
	
	@Test
	void testcreaStanze3() {
		assertEquals("Biblioteca", this.labirinto.getStanzaIngresso().getStanzaAdiacente("nord").getNome());

	}
	
	@Test
	void testcreaStanze4() {
		assertEquals("Aula N11", this.labirinto.getStanzaIngresso().getStanzaAdiacente("est").getNome());

	}
	
}
