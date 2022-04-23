package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//import graph.GrapheLA;
import graph.GrapheMA;
import graph.PCCDijkstra;

class PCCDijkstraTest {

	//private final static String[] NOEUDS = {"1", "2", "3", "4", "5", "6"};
	private final static String[] NOEUDS2 = {"1", "2", "3", "4"};
	
	@Test
	void test() {
		/*GrapheMA g = new GrapheMA(NOEUDS);
		assertEquals(NOEUDS.length, g.getNbNoeuds());
		g.ajouterArc("1","2", 2);
		g.ajouterArc("1","5", 2);
		g.ajouterArc("1","6", 1);
		g.ajouterArc("2","3", 0);
		g.ajouterArc("2","4", 4);
		g.ajouterArc("2","5", 5);
		g.ajouterArc("3","4", 1);
		g.ajouterArc("4","5", 2);
		g.ajouterArc("6","5", 2);
		PCCDijkstra.test(g, "1", "5");*/
		GrapheMA g1 = new GrapheMA(NOEUDS2);
		assertEquals(NOEUDS2.length, g1.getNbNoeuds());
		g1.ajouterArc("1","3", 1);
		g1.ajouterArc("3","2", 1);
		g1.ajouterArc("2","1", 1);
		g1.ajouterArc("1","4", 2);
		PCCDijkstra.test(g1, "1", "4");
		
	}
}
