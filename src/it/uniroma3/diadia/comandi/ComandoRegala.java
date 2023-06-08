package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando{

	private static final String REGALO = "regalo";
	private IO io;
	
	public ComandoRegala(String parametro, IO io) {
		this.io =io;
	}

	public void setIo(IO io) {
		this.io=io;
	}

	@Override
	public String getNome() {
		return REGALO;
	}
	
	@Override
	public void esegui(Partita partita) throws Exception {
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
		if(attrezzo!=null) {
			io.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzo, partita));
			partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());

		}
	}



}
