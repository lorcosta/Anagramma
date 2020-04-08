package it.polito.tdp.anagramma.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {
	
	private List<String> soluzione ;

	/**
	 * Genera tutti gli anagrammi della parola specificata in ingresso
	 * @param {@codeparola} parola da anagrammare
	 * @return elenco di tutti gli anagrammi della parola data
	 */
	public List<String> anagrammi(String parola) {
		this.soluzione=new ArrayList<String>();
		// caso iniziale, devo preparare le variabili necessarie all'avvio della ricorsione
		parola.toUpperCase();//così non ho problemi di differenza tra maiusc e minusc
		List<Character> disponibili= new ArrayList<Character>();
		for(int i=0;i<parola.length();i++) {
			disponibili.add(parola.charAt(i));
		}
		//avvio la ricorsione
		cerca("",0,disponibili);//"" perchè al primo giro non ho nessuna soluzione parziale, 
								//0 perchè sono al livello zero e passo i caratteri disponibili
		
		return soluzione;
	}
	/**
	 * Procedura ricorsiva per il calcolo dell'anagramma
	 * @param parziale parte iniziale dell'anagramma costruito finnora
	 * @param livello livello della ricorsione, sempre uguale a parziale.lenght()
	 * @param disponibili insieme delle lettere non ancora utilizzate
	 */
	private void cerca(String parziale, int livello, List<Character> disponibili) {
		if(disponibili.size()==0) {// equivalente a livello=parola.length()
			//caso terminale perchè non ci sono più caratteri da aggiungere
			this.soluzione.add(parziale);
		}
		
		//caso normale
		//provare ad aggiungere a 'parziale' tutti i caratteri presenti tra i disponibili
		for(Character ch:disponibili) {
			String tentativo= parziale+ch;
			List<Character> rimanenti= new ArrayList<>(disponibili);
			rimanenti.remove(ch);
			cerca(tentativo, livello+1, rimanenti);
		}
	}
}
/*
Dato di partenza: parola da anagrammare di lunghezza N
Soluzione parziale: una parte dell'anagramma già costruito (i primi caratteri)
Livello: indica il numero di lettere di cui è composta la soluzione parziale
Soluzione finale: soluzione di lunghezza N-> caso terminale
Caso terminale: salvare la soluzione trovata
Generazione delle nuove soluzioni: provare ad aggiungere una lettera, scegliendola 
	tra quelle che non sono ancofa state utilizzate (nella soluzione parziale corrente)
*/