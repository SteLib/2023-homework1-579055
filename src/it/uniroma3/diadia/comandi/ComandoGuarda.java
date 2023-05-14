package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

public class ComandoGuarda implements Comando{
	
	private final static String NOME = "guarda";
	private IO io;
	private Labirinto labirinto;
	
	public ComandoGuarda(IO io) {
		this.io=io;
	}
	
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Ciao giocatore "
						+ "Questa è la tua stanza corrente: " + labirinto.getStanzaCorrente());
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
		return null;
	}
	
	@Override
	public void setIo(IO io) {
		this.io = io;
		
	}

	@Override
	public String getNome() {
		return NOME;
	}

}
