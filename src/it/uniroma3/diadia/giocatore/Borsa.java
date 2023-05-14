package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String,Attrezzo> attrezzi;
	private int pesoMax;
	private int pesoAttuale;
	private int numeroAttrezzi;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi=new HashMap<String,Attrezzo>();
		this.numeroAttrezzi=0;
		this.pesoAttuale=0;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		this.numeroAttrezzi++;
		this.pesoAttuale += attrezzo.getPeso();
		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}
	
	public int getPeso() {
		return this.pesoAttuale;
	}
	
	public boolean getPesoRimanente(Attrezzo a) {
		if(a != null && this.getPesoMax()-this.getPeso()>=a.getPeso())
			return true;
		return false; 
	}

	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	public void removeAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo!=null) {
			if(this.attrezzi.containsKey(nomeAttrezzo)) 
				this.attrezzi.remove(nomeAttrezzo);
		}
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.attrezzi.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
				s.append(this.attrezzi.values().toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}

	public List <Attrezzo> getContenutoOrdinatoPerPeso() {
		final List <Attrezzo> risultato = new ArrayList<Attrezzo>(this.attrezzi.values());
		Collections.sort(risultato, new ComparatorePesoNome());

		return risultato;
	}

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		final SortedSet<Attrezzo> risultato = new TreeSet<Attrezzo>(new ComparatoreNome());
		risultato.addAll(this.attrezzi.values());
		return risultato;
	}

	public Map<Integer, Set<Attrezzo>>	getContenutoRaggruppatorePerPeso() {
		final Map<Integer, Set<Attrezzo>> peso2Attrezzi = new HashMap<>();
		//li guardo tutti
		for(Attrezzo attrezzo : this.attrezzi.values()) {
			Set<Attrezzo> delloStessoPeso=peso2Attrezzi.get(attrezzo.getPeso());
			if(delloStessoPeso==null) {
				//attrezzo di peso mai visto prima
				delloStessoPeso=new HashSet<>();
			}
			delloStessoPeso.add(attrezzo);
			peso2Attrezzi.put(attrezzo.getPeso(), delloStessoPeso);
		}
		return peso2Attrezzi;
	}

	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		final SortedSet<Attrezzo> risultato=new TreeSet<>(new ComparatorePesoNome());
		risultato.addAll(this.attrezzi.values());
		return risultato;
	}
	
}
