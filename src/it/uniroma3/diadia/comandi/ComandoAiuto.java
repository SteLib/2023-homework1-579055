package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	private String aiuto="aiuto";
	private IO io;
	static final private String[] ELENCO_COMANDI = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};

	public ComandoAiuto(String aiuto, IO io) {
		this.aiuto=aiuto;
		this.io=io;
	}

	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< ELENCO_COMANDI.length; i++) 
			io.mostraMessaggio(ELENCO_COMANDI[i]+" ");
	}

	@Override
	public void setParametro(String parametro) {
	}

	@Override
	public void setIo(IO io) {
		this.io = io;
		
	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public String getNome() {
		return aiuto;
	}


}
