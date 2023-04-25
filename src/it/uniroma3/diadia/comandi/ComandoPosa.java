package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class ComandoPosa implements Comando{
	private String posa="posa";
	private IO io;
	private String nomeAttrezzo;
	
	public ComandoPosa(String posa, IO io) {
		this.posa=posa;
		this.io=io;
	}


	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);

		if (attrezzo == null && partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo) == null) 
			return;
		if(attrezzo==null) {
			io.mostraMessaggio("nessun attrezzo da posare");
			return;
			}
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);

		io.mostraMessaggio("Il tuo attrezzo e' stato posato");

	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
	}


	@Override
	public String getParametro() {
		return nomeAttrezzo;
	}


	@Override
	public String getNome() {
		return posa;
	}
	
	@Override
	public void setIo(IO io) {
		this.io = io;
		
	}
}
