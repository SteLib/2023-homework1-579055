package it.uniroma3.diadia.personaggi;

import java.util.List;
import java.util.Random;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	Random random =new Random();

	public Strega(String nome, String descrizione) {
		super(nome, descrizione);
	}

	@Override
	public String agisci(Partita partita) {

		List<Stanza> stanzeAdiacenti=partita.getStanzaCorrente().getStanzeAdiacenti();
		String msg = null;

		if(!haSalutato())  {
			msg="Ciao mi  presento, io sono una strega e una strega può spostarti dove vuole";
			if(stanzeAdiacenti!=null && !stanzeAdiacenti.isEmpty()) {
				int indiceCasuale = random.nextInt(stanzeAdiacenti.size());
				Stanza stanzaCasuale = stanzeAdiacenti.get(indiceCasuale);
				partita.setStanzaCorrente(stanzaCasuale);
			}
		}
		else {
			msg = "già ci siamo incontrati, ora ti farò di nuovo cambiare Stanza aahahahhahah";
			if(stanzeAdiacenti!=null && !stanzeAdiacenti.isEmpty()) {
				Random random = new Random();
				int indiceCasuale = random.nextInt(stanzeAdiacenti.size());
				Stanza stanzaCasuale = stanzeAdiacenti.get(indiceCasuale);
				partita.setStanzaCorrente(stanzaCasuale);
			}
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		List<Attrezzo> attrezzi = partita.getGiocatore().getBorsa().getContenutoOrdinatoPerPeso();
		/*RIMUOVO UNO DEGLI ATTREZZI DEL GIOCATORE*/
		int indiceCasuale = random.nextInt(attrezzi.size());
		
		Attrezzo attezzoDaRimuovere= attrezzi.get(indiceCasuale);
		partita.getGiocatore().getBorsa().removeAttrezzo(attezzoDaRimuovere);
		
		String msg="ahahahahaahah grazie del tuo regalo giocatore";
		return msg;
	}

}
