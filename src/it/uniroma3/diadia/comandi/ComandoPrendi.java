package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**Comando prendi gli attrezzi presi vengono rimossi dalla stanza e aggiunti alla borsa*/
public class ComandoPrendi extends AbstractComando implements Comando{
	private final static String NOME = "prendi";
	private String nomeAttrezzo;
	

	@Override
	public void esegui(Partita partita) {

		Attrezzo attrezzo = partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);

		if (partita.getLabirinto().getStanzaCorrente() == null && attrezzo == null)
		return;
		if(attrezzo == null)	{
			this.getIo().mostraMessaggio(" nessun attrezzo da prendere");
			return;
		}
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzo);
		this.getIo().mostraMessaggio("Il tuo attrezzo e' stato preso");
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
		
	}
	
	@Override
	public String getNome() {
		return NOME;
	}
}
