package graph;

import java.util.ArrayList;

public class GrapheLA {
	private ArrayList<Integer> listeAdj;
	private int nbNoeuds;
	
	public GrapheLA(int nbNoeuds) {
		this.nbNoeuds = nbNoeuds;
		listeAdj = new ArrayList<>();
		for(int i = 0; i<nbNoeuds; i++) 
			listeAdj.add(0);
		
	}
	
	public int getNbNoeuds() {
		return nbNoeuds;
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
		StringBuilder sb = new StringBuilder();
		int indice = 0;
		int nbSucc;
		for(int noeud = 1; noeud<=getNbNoeuds(); noeud++) {
			nbSucc = listeAdj.get(indice);
			sb.append(noeud + " -> ");
			for(int succ = 0; succ<nbSucc; succ++) {
				indice++;
				sb.append(listeAdj.get(indice) + " ");
			}
			sb.append("\n");
			indice++;
			
		}
		return sb.toString();
	}
}
