package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	private final static String NOME = "aiuto";
	private IO io;
	static final private String[] ELENCO_COMANDI = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};

	public ComandoAiuto(IO io) {
		this.io=io;
	}

	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< ELENCO_COMANDI.length; i++) 
			io.mostraMessaggio(ELENCO_COMANDI[i]+" ");
		io.mostraMessaggio("");
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
		return NOME;
	}


}
