package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando{
	
	private String guarda="guarda";
	private IO io;
	
	public ComandoGuarda(String guarda, IO io) {
		this.guarda=guarda;
		this.io=io;
	}
	
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Ciao giocatore "
						+ "Questa è la tua stanza corrente: " + partita.getStanzaCorrente());
		io.mostraMessaggio("Questi sono i tuoi cfu: " + partita.getGiocatore().getCfu());
		io.mostraMessaggio("Questi sono i tuoi attrezzi: "+ partita.getGiocatore().getBorsa());
	}

	@Override
	public void setParametro(String parametro) {
		//
	}


	@Override
	public String getParametro() {
		//
		return guarda;
	}
	
	@Override
	public void setIo(IO io) {
		this.io = io;
		
	}

	@Override
	public String getNome() {
		return guarda;
	}

}
