package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando{
	
	private final static String NOME = "guarda";
	
	@Override
	public void esegui(Partita partita) {
		this.getIo().mostraMessaggio("Ciao giocatore "
						+ "Questa è la tua stanza corrente: " + partita.getStanzaCorrente());
		this.getIo().mostraMessaggio("Questi sono i tuoi cfu: " + partita.getGiocatore().getCfu()+ "CFU");
		this.getIo().mostraMessaggio("Questi sono i tuoi attrezzi: "+ partita.getGiocatore().getBorsa().toString());
	}
	
	@Override
	public String getNome() {
		return NOME;
	}

}
