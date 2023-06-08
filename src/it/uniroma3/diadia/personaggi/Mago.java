package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio{

	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private Attrezzo attrezzo;

	public Mago(String nome, String descrizione, Attrezzo attrezzo) {
		super(nome, descrizione);
		this.attrezzo=attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if(this.attrezzo!=null) {
			msg=MESSAGGIO_DONO;
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo=null;
		}
		else 
			msg=MESSAGGIO_SCUSE;
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg;

		if(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome())) {
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
			int pesoDimezzato=(partita.getGiocatore().getBorsa().getPeso())/2;
			attrezzo.setPeso(pesoDimezzato);
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo);
			msg="Il mago prenderà il tuo oggetto e lo renderà più leggero";
		}
		else 
			msg="Non hai nessun oggetto su cui il tuo mago possa fare modifiche";
		return msg;
	}
}
