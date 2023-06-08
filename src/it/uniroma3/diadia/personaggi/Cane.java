 package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	
	private Attrezzo osso; 
	private Attrezzo spada;
	
	public Cane(String nome, String descrizione) {
		super(nome, descrizione);
		this.osso= new Attrezzo("osso",1);
		this.spada= new Attrezzo("spada",7);

	}

	@Override
	public String agisci(Partita partita) {
		final String msg= "Argh Argh Argh,";
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);	
		return msg;  
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg;
		/*il cane accetta il suo cibo preferito ovvero l'osso e da un nuovo attrezzo al giocatore 
		 * altrimenti -1 cfu*/
		if(partita.getGiocatore().getBorsa().getAttrezzo(getNome())==this.osso) {
			partita.getGiocatore().getBorsa().removeAttrezzo("osso");
			partita.getGiocatore().getBorsa().addAttrezzo(spada);
			msg="Gnam gnam gnam," + 
				"Sembra essergli piaciuto, bene ora posso prendere la spada";
		}
		else {
			int attualiCfu = partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(attualiCfu-1);
			msg="ahiii mi ha morso" +
				"Consiglio: cerca l'attrezzo osso";
		}
		return msg;
	}

}
