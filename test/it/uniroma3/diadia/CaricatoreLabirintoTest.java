package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.StringReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class CaricatoreLabirintoTest {

	private final String monolocale =
			"Stanze:biblioteca\n"+
					"Magica:\n"+
					"Buia:\n"+
					"Bloccata:\n"+
					"Inizio:biblioteca\n"+
					"Vincente:biblioteca\n"+
					"Mago:\n"+
					"Cane:\n"+
					"Strega:\n"+
					"Attrezzi:\n"+
					"Uscite:\n";

	private final String bilocale =
			"Stanze:N12,N11\n"+
					"Magica:\n"+
					"Buia:\n"+
					"Bloccata:\n"+
					"Inizio:N12\n"+
					"Vincente:N11\n"+
					"Mago:\n"+
					"Cane:\n"+
					"Strega:\n"+
					"Attrezzi:spada 7 N12\n"+
					"Uscite:\n";
	
	private CaricatoreLabirinto labCaricato;

	@BeforeEach
	void setUp() throws Exception {

	}

	@Test
	void testCaricaMonolocale() throws FileNotFoundException, FormatoFileNonValidoException {
		this.labCaricato = new CaricatoreLabirinto(new StringReader(monolocale));
		labCaricato.carica();
		assertEquals("biblioteca" , this.labCaricato.getStanzaIniziale().getNome());
		assertEquals("biblioteca" , this.labCaricato.getStanzaVincente().getNome());
	}

	@Test
	void testCaricaBilocale() throws FileNotFoundException, FormatoFileNonValidoException {
		this.labCaricato = new CaricatoreLabirinto(new StringReader(bilocale));
		labCaricato.carica();
		assertEquals("N12" , this.labCaricato.getStanzaIniziale().getNome());
		assertEquals("N11" , this.labCaricato.getStanzaVincente().getNome());
	}

	
	@Test
	void testCaricaBilocaleConAttrezzo() throws FileNotFoundException, FormatoFileNonValidoException {
		this.labCaricato = new CaricatoreLabirinto(new StringReader(bilocale));
		labCaricato.carica();
		Attrezzo atteso = new Attrezzo("Spada",7);
		assertEquals(atteso , this.labCaricato.getStanzaVincente().getAttrezzo("Spada"));
	}

}
