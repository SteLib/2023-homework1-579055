package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{

	private String direzioneBloccata;
	private String nomeAttrezzoSblocca;

	public StanzaBloccata(String nome, String direzioneBloccata, String nomeAttrezzoSblocca) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.nomeAttrezzoSblocca = nomeAttrezzoSblocca;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzioneBloccata.equals(direzione) && !this.hasAttrezzo(nomeAttrezzoSblocca) )
			return this;
		return super.getStanzaAdiacente(direzione);
	}

	@Override
	public String getDescrizione() {
		String bloccata="la prossima stanza è bloccata:"+ "\ncerca un modo per sbloccarla,"+"\n magari un attrezzo?!";
		
		if(!this.hasAttrezzo(nomeAttrezzoSblocca))
			return bloccata;
		else 
			return super.getDescrizione();
	}
}
