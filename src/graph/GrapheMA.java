package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GrapheMA implements IGraph {
	private Map<String, Integer> noeuds;
	private String[] labels; // pour toString
	private boolean[][] mab;
	private int[][] mav;

	public GrapheMA(String[] labels) {
		this.labels = labels.clone();
		this.noeuds = new HashMap<>();
		int nb = labels.length;
		for (int i =0; i< nb; ++i)
			this.noeuds.put(labels[i], i);
		mab = new boolean[nb][nb]; 
		mav = new int[nb][nb];
	}
	
	@Override
	public int getNbNoeuds() { return mab.length; }
	@Override
	public boolean estNoeudOK(String label) {
		return noeuds.containsKey(label);
	}
	@Override
	public boolean estArcOK(String n1, String n2) {
		return estNoeudOK(n1) && estNoeudOK(n2);
	}
	
	
	@Override
	public void ajouterArc(String label1, String label2, int valeur) {
		assert ! aArc(label1,label2);
		int n1 = noeuds.get(label1);
		int n2 = noeuds.get(label2);
		mab[n1][n2] = true;
		mav[n1][n2] = valeur;
	}
	
	@Override
	public String toString() {
		String s = "";
		for(String label1 : noeuds.keySet()) {
			s+= label1 + " -> ";
			int n1 = noeuds.get(label1);
			for (int n2=0; n2< getNbNoeuds(); ++n2) {
				if (mab[n1][n2])
					s+= labels[n2] + "("+ mav[n1][n2] + ") ";
			}
			s+="\n";
		}
		return s;
	}
	@Override
	public boolean aArc(String label1, String label2) {
		assert estArcOK(label1,label2);
		return mab[noeuds.get(label1)][noeuds.get(label2)];
	}
	@Override
	public int getValeur(String label1, String label2) {
		assert estArcOK(label1, label2);
		return mav[noeuds.get(label1)][noeuds.get(label2)];
	}
	@Override
	public int dOut(String label) {
		assert estNoeudOK(label);
		int n1 = noeuds.get(label);
		int degre = 0;
		for (int n2 = 0; n2 < getNbNoeuds(); ++n2)
			if (mab[n1][n2])
				++degre;
		return degre;
	}
	@Override
	public int dIn(String label) {
		assert estNoeudOK(label);
		int n2 = noeuds.get(label);
		int degre = 0;
		for (int n1 = 0; n1 < getNbNoeuds(); ++n1)
			if (mab[n1][n2])
				++degre;
		return degre;
	}
	@Override
	public String[] getLabels() {
		return labels;
	}
	@Override
	public String[] getSucc(String predecesseur) {
		assert estNoeudOK(predecesseur);
		if(dOut(predecesseur) == 0) return new String[0];
		int cmp = 0;
		String[] tab = new String[dOut(predecesseur)];
		int n1 = noeuds.get(predecesseur);
		for (int n2 = 0; n2 < getNbNoeuds(); ++n2) {
			if (mab[n1][n2]) {
				tab[cmp] = labels[n2];
				++cmp;
			}
		}
		return tab;
	}

	@Override
	public int distance(ArrayList<String> cheminCalcule) {
		int value = 0;
		for(int i = 0; i < cheminCalcule.size() - 1; ++i) {
			value += getValeur(cheminCalcule.get(i), cheminCalcule.get(i + 1));
		}
		return value;
	}

}
