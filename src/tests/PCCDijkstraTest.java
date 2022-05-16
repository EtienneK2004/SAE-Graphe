package tests;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import graph.types.GrapheMA;
import pcc.IPCC;
import pcc.PCCDijkstra;

class PCCDijkstraTest {

	private final static String[] NOEUDS = {"A", "B", "C", "D", "E", "F"};
	private final static String[] NOEUDS2 = {"A", "B", "C", "D"};
	private final static String[] NOEUDS3 = {"A", "B", "C", "D", "E", "F"};
	
	
	// Rajouter un boolean dans les classe de graphe dès qu'il y a une pondération négative la fonction estOK
	
	private String[] toTab(String entrant, String sortant, IPCC pcc) {
		ArrayList<String> path = new ArrayList<>();
		pcc.chemin(entrant, sortant, path);
		String[] res = new String[path.size()];
		for(int i = 0; i < path.size(); ++i)
			res[i] = path.get(i);
		return res;
	}
	
	
	@Test
	void test() {
		GrapheMA g = new GrapheMA(NOEUDS);
		assertEquals(NOEUDS.length, g.getNbNoeuds());
		g.ajouterArc("A","B", 2);
		g.ajouterArc("A","E", 2);
		g.ajouterArc("A","F", 1);
		g.ajouterArc("B","C", 0);
		g.ajouterArc("B","D", 4);
		g.ajouterArc("B","E", 5);
		g.ajouterArc("C","D", 1);
		g.ajouterArc("D","E", 2);
		g.ajouterArc("F","E", 2);
		PCCDijkstra toto = new PCCDijkstra(g);
		String[] resAttendu = {"A","B","C","D"};
		assertArrayEquals(resAttendu, toTab("A","D", toto));
		assertEquals(3, toto.distance("A","D"));

		GrapheMA g1 = new GrapheMA(NOEUDS2);
		assertEquals(NOEUDS2.length, g1.getNbNoeuds());
		g1.ajouterArc("A","C", 1);
		g1.ajouterArc("C","B", 1);
		g1.ajouterArc("B","A", 1);
		g1.ajouterArc("A","D", 2);
		PCCDijkstra toto1 = new PCCDijkstra(g1);
		String[] resAttendu2 = {"A","D"};
		assertArrayEquals(resAttendu2, toTab("A","D", toto1));
		assertEquals(2, toto1.distance("A","D"));
		
		GrapheMA g3 = new GrapheMA(NOEUDS3);
		g3.ajouterArc("A","B", 1);
		g3.ajouterArc("A","C", 2);
		g3.ajouterArc("B","D", 3);
		g3.ajouterArc("D","E", 5);
		g3.ajouterArc("C","E", 4);
		PCCDijkstra toto3 = new PCCDijkstra(g3);
		String[] resAttendu3 = {"A","B","D"};
		assertArrayEquals(resAttendu3, toTab("A","D", toto3));
		assertEquals(4,toto3.distance("A","D"));
	}
}
