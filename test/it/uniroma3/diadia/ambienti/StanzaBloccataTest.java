package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	private StanzaBloccata stanzaLock;
	private Stanza stanzaNormale;
	private Attrezzo nomeAttrezzoSblocca;
	
	@BeforeEach
	void setUp() throws Exception {
		this.stanzaLock = new StanzaBloccata("StanzaBloccata", "passpartout" ,Direzione.ovest);
		this.nomeAttrezzoSblocca=new Attrezzo("passpartout", 1);
		this.stanzaNormale=new Stanza("camera");
		stanzaLock.impostaStanzaAdiacente(Direzione.ovest, stanzaNormale);
		
	}
	
	@Test
	public void testGetStanzaAdiacenteDirezioneBloccata() {
		assertEquals(stanzaLock, stanzaLock.getStanzaAdiacente(Direzione.ovest));
	}
	
	@Test
	public void testGetStanzaAdiacsenteDirezioneSbloccata() {
		stanzaLock.addAttrezzo(nomeAttrezzoSblocca);
		assertEquals(stanzaNormale, stanzaLock.getStanzaAdiacente(Direzione.ovest));
		
	}

	@Test
	public void testGetDescrizioneDirezioneSbloccata() {
		stanzaLock.addAttrezzo(nomeAttrezzoSblocca);
		assertEquals(stanzaLock.toString(), stanzaLock.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneDirezioneBloccata() {
		String e = "la prossima stanza è bloccata:"+ "\ncerca un modo per sbloccarla,"+"\n magari un attrezzo?!";
		assertEquals(e, stanzaLock.getDescrizione());
		
	}
	@Test
	void testDirezioneBlocataMaAttrezzoSblocca() {
		stanzaLock.addAttrezzo(nomeAttrezzoSblocca);
		assertEquals( stanzaNormale, stanzaLock.getStanzaAdiacente(Direzione.ovest));
	}

}
