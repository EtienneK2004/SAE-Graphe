package graph;

import java.util.Arrays;

public class GraphMA {
	
	private boolean[][] matriceAdj;
	
	public GraphMA(int nbNoeuds) {
		this.matriceAdj = new boolean[nbNoeuds][nbNoeuds];
		for(boolean[] ligne : matriceAdj)
			Arrays.fill(ligne, false);
	}
	
	public void ajouterArc(int entrant, int sortant) {
		
	}
	
	public boolean aArc(int entrant, int sortant) {
		return false;
	}
	
	public int dOut(int entrant) {
		return 0;
	}
	
	public int dIn(int sortant) {
		return 0;
	}
}
