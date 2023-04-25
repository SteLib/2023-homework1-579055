package it.uniroma3.diadia.ambienti;


public class StanzaBuia extends Stanza {

	private String lanterna;

	public StanzaBuia(String nome, String lanterna) {
		super(nome);
		this.lanterna= lanterna;

	}

	@Override
	public String getDescrizione() {
		String buio=new String();
		buio = "qui è buio pesto";
		if(hasAttrezzo(lanterna))
			return super.getDescrizione();
		return buio;
	}
}
