package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente il nome stanza buia */
	private static final String STANZA_BUIA_MARKER = "Buia:";  

	/* prefisso della riga contenente il nome stanza magica */
	private static final String STANZA_MAGICA_MARKER = "Magica:";  

	/* prefisso della riga contenente il nome stanza bloccata */
	private static final String STANZA_BLOCCATA_MARKER = "Bloccata:";  

	/* prefisso della riga contenente il nome del personaggio mago */
	private static final String PERSONAGGIO_MAGO = "Mago:";  

	/* prefisso della riga contenente il nome del personaggio mago */
	private static final String PERSONAGGIO_CANE = "Cane:";  

	/* prefisso della riga contenente il nome del personaggio mago */
	private static final String PERSONAGGIO_STREGA = "Strega:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(StringReader reader) throws FileNotFoundException{
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(reader);
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzaMagica();
			this.leggiECreaStanzaBuia();
			this.leggiECreaStanzaBloccata();
			this.leggiInizialeEvincente();
			this.leggiECreaPersonaggioMago();
			this.leggiECreaPersonaggioCane();
			this.leggiECreaPersonaggioStrega();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext()){
				result.add(scannerDiParole.next());
			}
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECreaStanzaMagica() throws FormatoFileNonValidoException  {
		String nomeStanzaMagica = this.leggiRigaCheCominciaPer(STANZA_MAGICA_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomeStanzaMagica)) {
			Stanza stanza = new StanzaMagica(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private void leggiECreaStanzaBuia() throws FormatoFileNonValidoException {
		String scpecificaStanze = this.leggiRigaCheCominciaPer(STANZA_BUIA_MARKER);
		
		for(String specifica : separaStringheAlleVirgole(scpecificaStanze)) {
			try(Scanner scannerDiLinee = new Scanner(specifica)) {
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("Non hai inserito il nome di una stanza Buia"));
				String nomeStanzaBuia = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("Non hai inserito il nome dell'attrezzo che sblocca la stanza Buia" +nomeStanzaBuia+"."));
				String lanterna = scannerDiLinee.next();
						
				
				Stanza stanzaBuia = new StanzaBuia(nomeStanzaBuia,lanterna);
				this.nome2stanza.put(nomeStanzaBuia, stanzaBuia);
			}
		}
	}

	private void leggiECreaStanzaBloccata() throws FormatoFileNonValidoException {
	    String specificaStanze = this.leggiRigaCheCominciaPer(STANZA_BLOCCATA_MARKER);

	    for (String specifica : separaStringheAlleVirgole(specificaStanze)) {
	        try (Scanner scannerDiLinee = new Scanner(specifica)) {
	            check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("Non hai speficifacto il nome di una stanza Bloccata"));
	            String nomeStanza = scannerDiLinee.next();
	            check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("Non hai specificato la direzione bloccata di " + nomeStanza + "."));
	           
	            String direzioneBloccataString = scannerDiLinee.next().toUpperCase();
	            Direzione direzioneBloccata = null;
	            for (Direzione direzione : Direzione.values()) {
	                if (direzione.name().equalsIgnoreCase(direzioneBloccataString)) {
	                    direzioneBloccata = direzione;
	                    break;  }
	            }
	            check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("Non hai specificato il nome dell'attrezzo che sblocca la " + direzioneBloccata + "."));
	            String nomeAttrezzoSblocca = scannerDiLinee.next();
	            if (direzioneBloccata == null) {
	                throw new FormatoFileNonValidoException("Direzione bloccata non valida: " + direzioneBloccataString);
	            }
	            
	            Stanza stanzaBloccata = new StanzaBloccata(nomeStanza, nomeAttrezzoSblocca, direzioneBloccata);
	            this.nome2stanza.put(nomeStanza, stanzaBloccata);
	        }
	    }
	}
	
	private void leggiECreaPersonaggioStrega() throws FormatoFileNonValidoException {
		String specificheStanze=this.leggiRigaCheCominciaPer(PERSONAGGIO_STREGA);
		
		for(String specificaStrega : separaStringheAlleVirgole(specificheStanze) ) {
			try(Scanner scannerDiLinee = new Scanner(specificaStrega)){
				check(scannerDiLinee.hasNext(),msgTerminazionePrecoce("Specifica il nome di una strega"));
				String nomeStrega=scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("Specifica la descrizione di " +nomeStrega+"."));
				String descrizione=scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("Specifica la stanza in cui si trova la strega" +nomeStrega+"."));
				String nomeStanza=scannerDiLinee.next();
				
				AbstractPersonaggio strega=new Strega(nomeStrega, descrizione);
				this.nome2stanza.get(nomeStanza).setPersonaggio(strega);
			}
		}
	}


	private void leggiECreaPersonaggioMago() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(PERSONAGGIO_MAGO);
		
		for(String specificaMago : separaStringheAlleVirgole(specificheStanze)) {
			try(Scanner scannerDiLinee = new Scanner(specificaMago)){
				check(scannerDiLinee.hasNext(),msgTerminazionePrecoce("Specifica il nome di un mago."));
				String nomeMago=scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("Problema con la descrizione del" + nomeMago + "."));
				String descrizione = scannerDiLinee.next();
				check(scannerDiLinee.hasNext(),msgTerminazionePrecoce("Problema con l'attrezzo che ha il mago"+ nomeMago+"."));
				String attrezzo= scannerDiLinee.next();
				check(scannerDiLinee.hasNext(), msgTerminazionePrecoce("Specifica la stanza dove mettere il mago"+nomeMago+".0"));
				String nomeStanza=scannerDiLinee.next();

				AbstractPersonaggio mago=new Mago(nomeMago, descrizione, new Attrezzo(attrezzo, 7));
				this.nome2stanza.get(nomeStanza).setPersonaggio(mago);
			}
		}
	}

	private void leggiECreaPersonaggioCane() throws FormatoFileNonValidoException {
		String specificheStanze=this.leggiRigaCheCominciaPer(PERSONAGGIO_CANE);

		for(String specificaCane : separaStringheAlleVirgole(specificheStanze)) {
			try (Scanner scannerLinea = new Scanner(specificaCane)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("Specifica il nome di un cane."));
				String nomeCane= scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("Problema con la descrizione del" + nomeCane + "."));
				String descrizione = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("Specifica il nome della stanza in cui collocare il cane " +nomeCane+ "."));
				String nomeStanza=scannerLinea.next();

				AbstractPersonaggio personaggio = new Cane(nomeCane, descrizione);
				this.nome2stanza.get(nomeStanza).setPersonaggio(personaggio);
			}
		}
	}


	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}
	
	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
	    String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
	    try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {
	        while (scannerDiLinea.hasNext()) {
	            check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("le uscite di una stanza."));
	            String stanzaPartenza = scannerDiLinea.next();
	            check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la direzione di una uscita della stanza " + stanzaPartenza));
	           
	            String direzioneString = scannerDiLinea.next().toUpperCase();
	            Direzione direzione = null;

	            for (Direzione d : Direzione.values()) {
	                if (d.name().equalsIgnoreCase(direzioneString)) {
	                    direzione = d;
	                    break; }
	            }
	            check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la destinazione di una uscita della stanza " + stanzaPartenza + " nella direzione " + direzione));
	            String stanzaDestinazione = scannerDiLinea.next();
	            if (direzione == null) {
	                throw new FormatoFileNonValidoException("Direzione uscita non valida: " + direzioneString);
	            }
	            impostaUscita(stanzaPartenza, direzione, stanzaDestinazione);
	        }
	    }
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, Direzione dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}
