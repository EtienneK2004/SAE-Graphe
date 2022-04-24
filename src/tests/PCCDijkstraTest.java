package tests;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//import graph.GrapheLA;
import graph.GrapheMA;
import graph.PCCDijkstra;

class PCCDijkstraTest {

	private final static String[] NOEUDS = {"A", "B", "C", "D", "E", "F"};
	private final static String[] NOEUDS2 = {"A", "B", "C", "D"};
	private final static String[] NOEUDS3 = {"A", "B", "C", "D", "E", "F"};
	
	
	// Rajouter un boolean dans les classe de graphe dès qu'il y a une pondération négative la fonction estOK
	
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
		toto.test("A", "D");

		GrapheMA g1 = new GrapheMA(NOEUDS2);
		assertEquals(NOEUDS2.length, g1.getNbNoeuds());
		g1.ajouterArc("A","C", 1);
		g1.ajouterArc("C","B", 1);
		g1.ajouterArc("B","A", 1);
		g1.ajouterArc("A","D", 2);
		PCCDijkstra toto1 = new PCCDijkstra(g1);
		toto1.test("A", "D");
		
		GrapheMA g3 = new GrapheMA(NOEUDS3);
		g3.ajouterArc("A","B", 1);
		g3.ajouterArc("A","C", 2);
		g3.ajouterArc("B","D", 3);
		g3.ajouterArc("D","E", 5);
		g3.ajouterArc("C","E", 4);
		PCCDijkstra toto3 = new PCCDijkstra(g3);
		toto3.test("A", "D");
	}
}
