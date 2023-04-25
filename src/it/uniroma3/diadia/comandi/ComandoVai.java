package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 */
public class ComandoVai implements Comando {
	private String direzione;
	private IO io;
	private String comando;
	
	public ComandoVai(String direzione, IO io) {
		this.direzione = direzione;
		this.io=io;
		this.comando="vai";
	}

	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente=partita.getStanzaCorrente();
		Stanza prossimaStanza=null;

		if (direzione==null) {
			io.mostraMessaggio("Dove vuoi andare? devi specificare una direzione");

			return;
		}
		
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if (prossimaStanza==null) {
			io.mostraMessaggio("Direzione inesitente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		io.mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

	@Override
	public String getParametro() {
		return direzione;
	}
	
	@Override
	public String getNome() {
		return comando;
	}
	
	@Override
	public void setIo(IO io) {
		this.io = io;
		
	}
}
