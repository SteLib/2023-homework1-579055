package it.uniroma3.diadia.comandi;


import java.util.Scanner;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Questa classe modella un comando.
 * Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro
 * su cui si applica il comando.
 * (Ad es. alla riga digitata dall'utente "vai nord"
 *  corrisponde un comando di nome "vai" e parametro "nord").
 *
 * @author  docente di POO
 * @version base
 */

public interface Comando {
	/*
	 * esecuzione del comando 
	 */
	public void esegui(Partita partita);
	
	/*
	 * set parametro del comando
	 */
	public void setParametro(String parametro);
	
	public String getParametro();
	
	public String getNome();

	void setIo(IO io);
}