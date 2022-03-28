package graph;

import java.util.Arrays;

public class GrapheMA {
	
	private boolean[][] matriceAdj;
	
	public GrapheMA(int nbNoeuds) {
		this.matriceAdj = new boolean[nbNoeuds][nbNoeuds];
		for(boolean[] ligne : matriceAdj)
			Arrays.fill(ligne, false);
	}
	
	public int getNbNoeuds() {
		return matriceAdj.length;
	}
	
	public void ajouterArc(int entrant, int sortant) {
		matriceAdj[entrant-1][sortant-1] = true;
	}
	
	public boolean aArc(int entrant, int sortant) {
		return matriceAdj[entrant-1][sortant-1];
	}
	
	public int dOut(int entrant) {
		int cpt = 0;
		for(boolean elem : matriceAdj[entrant-1])
			if(elem)
				cpt++;
		return cpt;
	}
	
	public int dIn(int sortant) {
		int cpt = 0;
		for(int i = 0; i < matriceAdj.length; i++)
			if(matriceAdj[i][sortant-1])
				cpt++;
		return cpt;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(boolean[] ligne : matriceAdj) {
			for(boolean elem : ligne) {
				if(elem) 
					sb.append("1 ");
				else
					sb.append("0 ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}



