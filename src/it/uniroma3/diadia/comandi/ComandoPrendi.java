package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**Comando prendi gli attrezzi presi vengono rimossi dalla stanza e aggiunti alla borsa*/
public class ComandoPrendi implements Comando{
	private String prendi="prendi";
	private IO io;
	private String nomeAttrezzo;
	
	public ComandoPrendi(String prendi, IO io) {
		this.prendi = prendi;
		this.io = io;
	}
	
	public ComandoPrendi() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void esegui(Partita partita) {

		Attrezzo attrezzo = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);

		if (partita.getStanzaCorrente() == null && attrezzo == null)
		return;
		if(attrezzo == null)	{
			io.mostraMessaggio(" nessun attrezzo da prendere");
			return;
		}
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		partita.getStanzaCorrente().removeAttrezzo(attrezzo);
	

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
		return prendi;
	}
}
