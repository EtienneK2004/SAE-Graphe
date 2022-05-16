package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import graph.GrapheMA;
import pcc.IPCC;
import pcc.PCCBellman;

class PCCBellmanTest {
	private final static String[] NOEUDS = {"A", "B", "C", "D", "E", "F"};
	private final static String[] NOEUDS2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
	
	private String[] toTab(String entrant, String sortant, IPCC pcc) {
		ArrayList<String> path = new ArrayList<>();
		pcc.chemin(entrant,sortant, path);
		String[] res = new String[path.size()];
		for(int i = 0; i < path.size(); ++i)
			res[i] = path.get(i);
		for(String s : res)
			System.out.println(s);
		return res;
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
		assertArrayEquals(resAttendu,toTab("1", "6", blman));
			
		
		assertEquals(16, blman.distance("1", "6"));
	}
}
