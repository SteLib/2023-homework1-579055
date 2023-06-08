package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class ComandoPosa extends AbstractComando  {
	private final static String NOME = "posa";
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);

		if (attrezzo == null && partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo) == null) 
			return;
		if(attrezzo==null) {
			this.getIo().mostraMessaggio("nessun attrezzo da posare");
			return;
			}
		partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo);
		partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);

		this.getIo().mostraMessaggio("Il tuo attrezzo e' stato posato");

	}

	@Override
	public String getNome() {
		return NOME;
	}
	
}

