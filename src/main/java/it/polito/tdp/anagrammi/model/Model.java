package it.polito.tdp.anagrammi.model;

import java.util.HashSet;
import java.util.Set;

import it.polito.tdp.anagrammi.dao.AnagrammaDAO;

public class Model {
	
	private Set<Anagramma> listaAnagrammi;
	private AnagrammaDAO anagrammaDAO;

	public Model() {
		super();
		this.anagrammaDAO = new AnagrammaDAO();
		this.listaAnagrammi = new HashSet<Anagramma>();
	}
	
	public Set<Anagramma> getTutteLeParole(){
		return this.anagrammaDAO.getTutteLeParole();
	}
	
	
	public Set<Anagramma> anagramma(String s) {
		listaAnagrammi.clear();
		anagramma_ricorsiva("", 0, s);
		return listaAnagrammi;
	}
	
	private void anagramma_ricorsiva(String parziale, int livello, String rimanenti) {
		if (rimanenti.length() == 0) { /* caso terminale */
			Anagramma a = new Anagramma(parziale, anagrammaDAO.isCorrect(parziale));
			if(!listaAnagrammi.contains(a))
				listaAnagrammi.add(a);
		} else {
			/* caso normale */
			// es: parziale="AC", livello=2, rimanenti="BD"
			for (int pos = 0; pos < rimanenti.length(); pos++) {
				String nuova_parziale = parziale + rimanenti.charAt(pos);
				String nuova_rimanenti = rimanenti.substring(0, pos) + rimanenti.substring(pos + 1); //stringa rimanente vecchia meno il carattere pos (substring esclude estremo superiore)
				anagramma_ricorsiva(nuova_parziale, livello + 1, nuova_rimanenti);
			}
		}
	}
	

}
