package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{
	private String nonValido ="non valido";
	private IO io;
	
	public ComandoNonValido(String nonValido, IO io) {
		this.nonValido=nonValido;
		this.io=io;
	}
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Il comando non è valido, scrivi 'aiuto' ");	
	}

	@Override
	public void setParametro(String parametro) {
		//
	}
	@Override
	public String getParametro() {
		return nonValido;
	}
	@Override
	public String getNome() {
		return nonValido;
	}
	@Override
	public void setIo(IO io) {
		this.io = io;
		
	}
}
