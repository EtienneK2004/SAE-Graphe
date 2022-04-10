package tests;
// Test donnés dans le sujet
import static org.junit.jupiter.api.Assertions.*;

import graph.GrapheMA;
import org.junit.jupiter.api.Test;

class GrapheMATest {
	private final static String[] NOEUDS = {"1", "2", "3", "4", "5", "6"};
	@Test
	void test() {
		GrapheMA g = new GrapheMA(NOEUDS);
		assertEquals(NOEUDS.length, g.getNbNoeuds());
		g.ajouterArc("1","2", 1);
		g.ajouterArc("1","3", 2);
		g.ajouterArc("1","4", 3);
		g.ajouterArc("1","5", 1);
		g.ajouterArc("2","5", 5);
		g.ajouterArc("4","4", 1);
		g.ajouterArc("5","1", 5);
		assertTrue(g.aArc("1","5"));
		assertTrue(g.aArc("4","4"));
		assertTrue(g.aArc("5","1"));
		assertFalse(g.aArc("4","1"));
		assertFalse(g.aArc("6","6"));
		assertEquals(4,g.dOut("1")); // degré sortant
		assertEquals(1,g.dOut("2"));
		assertEquals(0,g.dOut("3"));
		assertEquals(1,g.dOut("5"));
		assertEquals(0,g.dOut("6"));
		assertEquals(1, g.dIn("1")); // degré entrant
		assertEquals(2, g.dIn("4"));
		assertEquals(2, g.dIn("5"));
		assertEquals(0, g.dIn("6"));
		assertTrue(g.toString().contentEquals(
		"1 -> 2(1) 3(2) 4(3) 5(1) \n"+
		"2 -> 5(5) \n"+
		"3 -> \n"+
		"4 -> 4(1) \n"+
		"5 -> 1(5) \n"+
		"6 -> \n"));
	}
}
