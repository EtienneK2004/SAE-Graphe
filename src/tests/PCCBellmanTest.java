package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import graph.GrapheMA;
import pcc.PCCBellman;

class PCCBellmanTest {
	private final static String[] NOEUDS = {"A", "B", "C", "D", "E", "F"};
	private final static String[] NOEUDS2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
	@Test
	void testTriNiveau() {
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
		PCCBellman blman = new PCCBellman(g);
		String[] resAttendu = {"A", "F", "B", "C", "D", "E"};
		assertArrayEquals(resAttendu, blman.getTriNiveau());
	}

	
	@Test
	void testAlgorithme() {
		GrapheMA g = new GrapheMA(NOEUDS2);
		assertEquals(NOEUDS2.length, g.getNbNoeuds());
		g.ajouterArc("1","2",3);
		g.ajouterArc("2","3",7);
		g.ajouterArc("3","4",2);
		g.ajouterArc("4","5",3);
		g.ajouterArc("1","5",3);
		g.ajouterArc("3","5",7);
		g.ajouterArc("5","7",8);
		g.ajouterArc("4","8",6);
		g.ajouterArc("2","9",7);
		g.ajouterArc("9","6",8);
		g.ajouterArc("8", "6", 3);
		g.ajouterArc("7", "6", 5);
		PCCBellman blman = new PCCBellman(g);
		String[] resAttendu = {"1", "5", "7", "6"};
		assertArrayEquals(resAttendu, blman.chemin("1", "6"));
			
		
		assertEquals(16, blman.distance("1", "6"));
	}
}
