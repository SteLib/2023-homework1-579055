package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{
	private final static String NOME = "non valido";
	private IO io;
	
	public ComandoNonValido(IO io) {
		this.io=io;
	}
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Il comando non è valido, scrivi 'aiuto' ");	
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getNome() {
		return NOME;
	}
	
	@Override
	public void setIo(IO io) {
		this.io = io;
		
	}
}
