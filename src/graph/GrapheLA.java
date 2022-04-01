package graph;

import java.util.ArrayList;

public class GrapheLA {
	private ArrayList<Integer> listeAdj;
	private int nbNoeuds;
	
	public GrapheLA(int nbNoeuds) {
		this.nbNoeuds = nbNoeuds;
		listeAdj = new ArrayList<>();
	}
	
	public int getNbNoeuds() {
		return nbNoeuds+1-1;
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
	
	@Override
	public String toString() {
		return super.toString();
	}
}
