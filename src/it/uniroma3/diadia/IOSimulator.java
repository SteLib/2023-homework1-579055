package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

public class IOSimulator implements IO{

	private int indiceMessaggiMostrati;
	private List<String> comandiLetti;
	private List<String> messaggiStampati;
	private int indiceRigheLette;

	public IOSimulator (List<String> comandiLetti) {
		this.comandiLetti=comandiLetti;
		this.messaggiStampati= new ArrayList<String>();
		this.indiceMessaggiMostrati=0;
		this.indiceRigheLette=0;
	}
	
	public List<String> getMessaggiStampati() {
		return messaggiStampati;
	}

	public void setMessaggiStampati(List<String> messaggiStampati) {
		this.messaggiStampati = messaggiStampati;
	}
	
	@Override
	public void mostraMessaggio (String messaggio) {
		this.messaggiStampati.add(indiceMessaggiMostrati, messaggio);
		indiceMessaggiMostrati++;
	}


	@Override
	public String leggiRiga() {
		String riga=null;
		if (this.comandiLetti.isEmpty())
			return riga;
		else
		riga = this.comandiLetti.get(indiceRigheLette);
		indiceRigheLette++;
		return riga;
	}

	

}
