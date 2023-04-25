package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando{
	private String fine="fine";
	private IO io;
	

	
	public ComandoFine(String parametro, IO io) {
		this.fine="fine";
		this.io=io;
	}

	/**
	 * Comando "Fine".
	 */
	
	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
		io.mostraMessaggio("Grazie di aver giocato!");
	}
	
	@Override
	public void setParametro(String parametro) {
		
	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public String getNome() {
		return fine;
	}
	
	@Override
	public void setIo(IO io) {
		this.io = io;
		
	}


}