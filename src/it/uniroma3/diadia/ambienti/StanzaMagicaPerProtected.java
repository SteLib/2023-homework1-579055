package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaPerProtected extends StanzaBasePerProtected{

	protected int contatore;
	protected int sogliaMagica;
	final static public int SOGLIA_MAGICA_DEFAULT = 3;


	public StanzaMagicaPerProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	
	public StanzaMagicaPerProtected(String nome, int soglia) {
		super(nome);
		this.contatore=0;
		this.sogliaMagica=soglia;
	}

	protected Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(),pesoX2);
		return attrezzo;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		//il comportamento non è ancora magico
		if(this.contatore<this.sogliaMagica)
			this.contatore++;
		else //il comportamento è magico
			attrezzo=this.modificaAttrezzo(attrezzo);

		return super.addAttrezzo(attrezzo);
	}
}
