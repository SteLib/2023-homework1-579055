package it.uniroma3.diadia;

import java.util.Scanner;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *         
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	
	public DiaDia() {
		this.partita = new Partita();
	  
		}

	public void gioca(IOConsole console) {
		String istruzione; 
		Scanner scannerDiLinee;

		System.out.println(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(console.leggiRiga());		
		do		
			istruzione = scannerDiLinee.nextLine();
		while (!processaIstruzione(istruzione, console));}   



	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione, IOConsole console) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(console); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro(), console);
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto(console);
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro(), console);
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro(),console);
		
		else
			System.out.println("Comando sconosciuto");
		if (this.partita.vinta()) {
			System.out.println("Hai vinto!");
			return true;
		} else
			return false;
	}   
	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto(IOConsole console) {
		for(int i=0; i< elencoComandi.length; i++) 
			console.mostraMessaggio(elencoComandi[i]+" ");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione, IOConsole console) {
		if(direzione==null)
			console.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}


	/**Comando prendi gli attrezzi presi vengono rimossi dalla stanza e aggiunti alla borsa*/
	private Attrezzo prendi (String nomeAttrezzo, IOConsole console) {
		if (nomeAttrezzo != null && partita.getStanzaCorrente() != null) {
			Attrezzo attrezzo = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			if (attrezzo != null) {
				partita.giocatore.getBorsa().addAttrezzo(attrezzo);
				partita.getStanzaCorrente().removeAttrezzo(attrezzo);
				
				console.mostraMessaggio("Il tuo attrezzo e' stato preso");
				
				return attrezzo;
			}
		}
		console.mostraMessaggio("Non ci sono attrezzi da prendere");
		return null;
	}


	/**Comando posa*/
	private void posa (String nomeAttrezzo, IOConsole console)  {
		if (nomeAttrezzo != null && partita.giocatore.getBorsa().getAttrezzo(nomeAttrezzo) != null) {
			Attrezzo attrezzo = partita.giocatore.getBorsa().getAttrezzo(nomeAttrezzo);
			if (attrezzo != null) {
				partita.getStanzaCorrente().addAttrezzo(attrezzo);
				partita.giocatore.getBorsa().removeAttrezzo(nomeAttrezzo);
				
				console.mostraMessaggio("Il tuo attrezzo e' stato posato");
				
				
			}
		}
		console.mostraMessaggio("Non ci sono attrezzi da posare");
	}





	/**
	 * Comando "Fine".
	 */
	private void fine(IOConsole console) {
		console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		IOConsole console= new IOConsole();
		gioco.gioca(console);
	}
}