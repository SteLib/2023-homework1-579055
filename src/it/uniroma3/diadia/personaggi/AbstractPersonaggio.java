package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {
	private String nome;
	private String descrizione;
	private boolean haSalutato;

	public AbstractPersonaggio(String nome,String descrizione) {
		this.nome=nome;
		this.descrizione=descrizione;
		this.haSalutato=false;
	}

	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setPresentazione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getPresentazione() {
		return this.descrizione;
	}

	public boolean haSalutato() {
		return this.haSalutato;
	}

	@Override
	public String toString() {
		return this.getNome();
	}
	
	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao, io sono: ");
		risposta.append(this.getNome()+'.');

		if(!haSalutato) {
			risposta.append(this.descrizione);
		}
		else 
			risposta.append("Ci siamo già incontrati");
		this.haSalutato=true;
		return risposta.toString();
	}

	abstract public String agisci(Partita partita);
	
	abstract public  String riceviRegalo(Attrezzo attrezzo, Partita partita);

}
