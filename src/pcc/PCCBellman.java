package pcc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import graph.IGraph;

public class PCCBellman implements IPCC{
	private static final int INFINI = Integer.MAX_VALUE;
	private IGraph graph;
	private String[] labelsTriNiveau;
	private int[] d;       //distances du point de départ
	private String[] p;    //Prédécesseur optimal pour chaque point
	private boolean tri = false;
	private boolean calcule = false;
	public PCCBellman(IGraph graph) {
		this.graph = graph;
		labelsTriNiveau = new String[graph.getNbNoeuds()];
		d = new int[graph.getNbNoeuds()];
		p = new String[graph.getNbNoeuds()];
	}
	
	private void triNiveau() {
		int k = 0;
		int n = graph.getNbNoeuds();
		HashMap<String,Integer> degPre = new HashMap<>();
		LinkedList<LinkedList<String>> noeudsTri = new LinkedList<>();
		
		
		//initialisation avec le nombre de predecesseurs
		for(String X : graph.getLabels()) {
			degPre.put(X, graph.dIn(X));
		}
		
		//On trouve le sommet de depart, qui n'a pas de predecesseurs
		for(String X : graph.getLabels()) {
			if(degPre.get(X) == 0) {
				LinkedList<String> l = new LinkedList<>();
				l.push(X);
				noeudsTri.push(l);
			}
		}
		
		//On fait une liste pour chaque niveau, chaque liste contient les noeuds du niveau
		while(noeudsTri.get(k).size() > 0 && k<10) {
			noeudsTri.addLast(new LinkedList<>());
			for(String X : noeudsTri.get(k)) {
				for(String Y : graph.getSucc(X)) {
					degPre.put(Y, degPre.get(Y)-1);
					if(degPre.get(Y) == 0) {
						noeudsTri.get(k+1).push(Y);
					}
				}
			}
			k++;
		}
		
		
		//On transforme la liste de listes en tableau à une dimension
		
		for(int i = 0; i<n; i++) {
			if(noeudsTri.getFirst().size() == 0)
				noeudsTri.pop();
			labelsTriNiveau[i] = noeudsTri.getFirst().pop();
			
		}
		
		
		
		
		tri = true;
	}
	
	public String[] getTriNiveau() {
		if(!tri) {
			triNiveau();
		}
		return labelsTriNiveau;
	}
	
	private int indexOfMinDistance(String label) {
		int min = -1;
		int iMin = -1;
		for(String autreNoeud : graph.getLabels()) {
			if(graph.aArc(autreNoeud, label)) {
				int c = graph.getValeur(autreNoeud, label) + d[Arrays.asList(labelsTriNiveau).indexOf(autreNoeud)];
				if(min == -1 || min > c) {
					min = c;
					iMin = Arrays.asList(labelsTriNiveau).indexOf(autreNoeud);
				}
			}
		}
			
		return iMin;
	}
	
	
	
	//Calcule les plus courtes distances et les predecesseurs optimum
	private void algorithme(String debut, String fin) {
		getTriNiveau(); //Fait le tri si il est pas déjà fait
		
		d[0] = 0;
		p[0] = null;
		int n = graph.getNbNoeuds();
		for(int i = 1; i < n; i++) {
			d[i] = INFINI;
			p[i] = null;
		}
		int idxM;
		for(int i = 1; i < n; i++) {
			idxM = indexOfMinDistance(labelsTriNiveau[i]);
			d[i] = graph.getValeur(labelsTriNiveau[idxM], labelsTriNiveau[i]) + d[idxM];
			p[i] = labelsTriNiveau[idxM];
		}
		
		calcule = true;
		
	}

	
	//renvoie le plus court chemin entre 2 noeud
	@Override
	public int chemin(String debut, String fin, ArrayList<String> path) {
		if(!calcule) algorithme(debut, fin);

		String nCourant = fin;
		while(nCourant != debut) {
			path.add(nCourant);
			nCourant = p[Arrays.asList(labelsTriNiveau).indexOf(nCourant)];
			
		}
		path.add(debut);
		
		ArrayList<String> pathFinal = new ArrayList<String>();
		for(int i = path.size() - 1; i >= 0; --i)
			pathFinal.add(path.get(i));
		path.clear();
		for(int i = 0; i < pathFinal.size(); i++)
			path.add(pathFinal.get(i));
		
		return distance(debut, fin);
	}
	
	
	//renvoie la plus courte disctance entre 2 noeud
	@Override
	public int distance(String debut, String fin) {
		if(!calcule) algorithme(debut, fin);
		return d[Arrays.asList(labelsTriNiveau).indexOf(fin)];
	}
}
