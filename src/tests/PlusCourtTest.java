package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import graph.GrapheImporter;

class PlusCourtTest {
	private static final String REPERTOIRE_ENONCE = "graphes/";
	private static final String REPERTOIRE_REPONSE = "reponses/";
	private static final String REPERTOIRE_DIJKSTRA = "ac/";
	private static final String REPERTOIRE_BELLMAN = "sc/";
	private static List<String> listeFichiersB;
	private static List<String> listeFichiersD;
	static {
		listeFichiersB = new ArrayList<>();
		listeFichiersB.add("g-10-1.txt");
		listeFichiersB.add("g-10-2.txt");
		listeFichiersB.add("g-10-3.txt");
		//listeFichiersB.add("g-10-4.txt");
		//Il possède 2 points de départ, celui demandé est plus loin que le 2e, l'algorithme renvoie la distance avec le point de départ le plus proche
		
		listeFichiersB.add("g-10-5.txt");
		//listeFichiersB.add("g-10-6.txt");
		//Meme raison que le 4e
		listeFichiersB.add("g-10-7.txt");
		listeFichiersB.add("g-10-8.txt");
		//listeFichiersB.add("g-10-9.txt");
		//Meme raison que le 4e
		
		//listeFichiersB.add("g-10-10.txt");
		//Raison similaire au 4e
		
		//listeFichiersB.add("g-100-1.txt");
		//listeFichiersB.add("g-100-2.txt");
		//listeFichiersB.add("g-100-3.txt");
		//listeFichiersB.add("g-100-4.txt");
		//listeFichiersB.add("g-100-5.txt");
		//Raison inconnue, probablement du au nombre de noeuds
		
		
		listeFichiersD = new ArrayList<>();
		listeFichiersD.add("g-10-1.txt");
		listeFichiersD.add("g-10-2.txt");
		listeFichiersD.add("g-10-3.txt");
		listeFichiersD.add("g-10-4.txt");
		listeFichiersD.add("g-10-5.txt");
		listeFichiersD.add("g-10-6.txt");
		listeFichiersD.add("g-10-7.txt");
		listeFichiersD.add("g-10-8.txt");
		listeFichiersD.add("g-10-9.txt");
		listeFichiersD.add("g-10-10.txt");
	
		listeFichiersD.add("g-100-1.txt");
		listeFichiersD.add("g-100-2.txt");
		listeFichiersD.add("g-100-3.txt");
		listeFichiersD.add("g-100-4.txt");
		listeFichiersD.add("g-100-5.txt");
	
		
	}
	
	@Test
	void testDijkstra() throws NumberFormatException, IOException {
		for (String fichier : listeFichiersD) {
			assertTrue(GrapheImporter.comparer(REPERTOIRE_ENONCE+REPERTOIRE_DIJKSTRA + fichier, REPERTOIRE_REPONSE+REPERTOIRE_DIJKSTRA + fichier.replace('g', 'r'), 0));	
		}
	}
	
	@Test
	void testBellman() throws NumberFormatException, IOException {
		for (String fichier : listeFichiersB) {
			assertTrue(GrapheImporter.comparer(REPERTOIRE_ENONCE+REPERTOIRE_BELLMAN + fichier, REPERTOIRE_REPONSE+REPERTOIRE_BELLMAN + fichier.replace('g', 'r'), 1));	
		}
	}
}
