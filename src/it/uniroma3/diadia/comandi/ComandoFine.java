package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {
	private final static String NOME = "fine";
	
	/**
	 * Comando "Fine".
	 */

	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
		this.getIo().mostraMessaggio("Grazie di aver giocato!");
	}

	@Override
	public String getNome() {
		return NOME;
	}

}
