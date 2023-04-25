package it.uniroma3.diadia;

public class IOSimulator implements IO{

	private int indiceProxComando;
	private String[] comandiLetti;
	private String[] messaggiStampati;
	private int indiceMessaggiStampati;

	public IOSimulator (String[] comandiLetti) {
		this.comandiLetti=comandiLetti;
		this.indiceProxComando=0;
		this.indiceMessaggiStampati=0;
		this.messaggiStampati= new String[50];
	}

	@Override
	public void mostraMessaggio (String messaggio) {
		messaggio = this.messaggiStampati[this.indiceMessaggiStampati];
		indiceMessaggiStampati++;
	}


	@Override
	public String leggiRiga() {
		if (this.comandiLetti.length == 0)
			return null;
		else
			return this.comandiLetti[this.indiceProxComando++];
	}

	public String[] getMessaggiStampati() {
		return messaggiStampati;
	}

	public void setMessaggiStampati(String[] messaggiStampati) {
		this.messaggiStampati = messaggiStampati;
	}


}
