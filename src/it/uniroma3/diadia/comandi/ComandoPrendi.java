package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**Comando prendi gli attrezzi presi vengono rimossi dalla stanza e aggiunti alla borsa*/
public class ComandoPrendi implements Comando{
	private final static String NOME = "prendi";
	private IO io;
	private String nomeAttrezzo;
	
	public ComandoPrendi(String parametro, IO io) {
		this.nomeAttrezzo=parametro;
		this.io = io;
	}

	@Override
	public void esegui(Partita partita) {

		Attrezzo attrezzo = partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);

		if (partita.getLabirinto().getStanzaCorrente() == null && attrezzo == null)
		return;
		if(attrezzo == null)	{
			io.mostraMessaggio(" nessun attrezzo da prendere");
			return;
		}
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzo);
		io.mostraMessaggio("Il tuo attrezzo e' stato preso");
	}
	
	@Override
	public void setIo(IO io) {
		this.io = io;
		
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
		
	}

	@Override
	public String getParametro() {
		return nomeAttrezzo;
	}

	@Override
	public String getNome() {
		return NOME;
	}
}
