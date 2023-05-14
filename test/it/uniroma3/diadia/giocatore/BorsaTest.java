package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	private Borsa vuota;
	private Borsa nonVuota;
	private Attrezzo osso;
	private Attrezzo spada;
	private Attrezzo pala;
	private Attrezzo ascia;
	private Attrezzo martello;
	private Attrezzo arco;
	private Map<String,Attrezzo> attrezzi;

	@BeforeEach
	public void setUp() {
		this.vuota = new Borsa();
		this.nonVuota= new Borsa();
		this.osso= new Attrezzo("osso", 1);
		this.spada=new Attrezzo("spada", 5);
		this.pala=new Attrezzo ("pala",2);
		this.ascia=new Attrezzo("ascia", 7);
		this.martello=new Attrezzo("martello", 12);
		this.arco=new Attrezzo("arco",5);
	}

	@Test
	void testAddAttrezzo() {
		this.vuota.addAttrezzo(osso);
		assertTrue(this.vuota.hasAttrezzo("osso"));
	}

	@Test
	void testIsEmpty() {
		attrezzi=new HashMap<String,Attrezzo>();
		assertTrue(this.attrezzi.isEmpty());
	}

	@Test
	public void testGetPeso() {
		vuota.addAttrezzo(osso);
		assertEquals(osso, vuota.getAttrezzo("osso"));

	}  


	@Test
	void testHasAttrezzo() {
		assertFalse(this.vuota.hasAttrezzo("osso"));
	}

	@Test
	void testRemoveAttrezzo() {
		this.nonVuota.removeAttrezzo("osso");
		assertFalse(this.nonVuota.hasAttrezzo("osso"));
	}

	@Test
	public void testGetSortedSetOrdinatoPerPesoAttrezziDiversiPesoDiverso() {
		this.nonVuota.addAttrezzo(ascia);
		this.nonVuota.addAttrezzo(martello);

		Set<Attrezzo> valoreAtteso=new TreeSet<>(new ComparatoreNome());
		valoreAtteso.add(martello);
		valoreAtteso.add(ascia);
		assertArrayEquals(valoreAtteso.toArray(), nonVuota.getContenutoOrdinatoPerPeso().toArray());
	}

	@Test
	void testGetSortedSetOrdinatoPerPesoAttrezziStessoPeso() {
		this.nonVuota.addAttrezzo(spada);
		this.nonVuota.addAttrezzo(arco);

		Set<Attrezzo> valoreAtteso=new TreeSet<>(new ComparatorePesoNome());
		valoreAtteso.add(spada);
		valoreAtteso.add(arco);
		assertArrayEquals(valoreAtteso.toArray(), nonVuota.getSortedSetOrdinatoPerPeso().toArray());
	}
	
	
	@Test
	public void testGetContenutoRaggruppatoPerPesoPesiDiversiSingoloElemento() {
		this.nonVuota.addAttrezzo(pala);
		this.nonVuota.addAttrezzo(ascia);
		
		Map<Integer, Set<Attrezzo>> attrezzi = new TreeMap<>();
		Set<Attrezzo> singolo1= new TreeSet<>();
		Set<Attrezzo> singolo2= new TreeSet<>();
		
		singolo1.add(ascia);
		singolo2.add(pala);

		attrezzi.put(2, singolo1);
		attrezzi.put(3, singolo2);
		
		System.out.println(attrezzi);
		System.out.println(nonVuota.getContenutoRaggruppatorePerPeso());
		assertEquals(attrezzi, nonVuota.getContenutoRaggruppatorePerPeso());
	
	}
		

}

