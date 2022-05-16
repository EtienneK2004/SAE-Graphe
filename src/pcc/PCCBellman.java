package pcc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import graph.IGraph;

public class PCCBellman implements IPCC{
	private static final int INFINI = Integer.MAX_VALUE;
	private IGraph graph;
	private HashMap<String, Integer> d;       //distances du point de départ
	private HashMap<String, String> p;    //Prédécesseur optimal pour chaque point

	private boolean calcule = false;
	
	
	
	
	
	public PCCBellman(IGraph graph) {
		this.graph = graph;
		d = new HashMap<>();
		p = new HashMap<>();
	}

	private void algo(String debut) {
		d = new HashMap<>();
		p = new HashMap<>();
		for(String u : graph.getLabels()) {
			d.put(u, INFINI);
			p.put(u, null);
		}
		d.put(debut, 0);
		for(int k = 1; k<graph.getNbNoeuds();k++) {
			for(String u : graph.getLabels()) {
				for(String v : graph.getSucc(u)) {
					if(d.get(u) >= 0 && d.get(u) < INFINI && d.get(u) + graph.getValeur(u, v) < d.get(v)) {
						d.put(v, d.get(u) + graph.getValeur(u, v));
						p.put(v, u);
					}
				}
			}
		}
		
	
	}

	
	//renvoie le plus court chemin entre 2 noeud
	@Override
	public int chemin(String debut, String fin, ArrayList<String> path) {
		if(!calcule) algo(debut);

		String nCourant = fin;
		while(nCourant != null) {
			path.add(nCourant);
			nCourant = p.get(nCourant);
			
		}
		
		ArrayList<String> pathFinal = new ArrayList<String>();
		for(int i = path.size() - 1; i >= 0; --i)
			pathFinal.add(path.get(i));
		path.clear();
		if(!(pathFinal.get(0).equals(debut) && pathFinal.get(pathFinal.size()-1).equals(fin)) )
			return Integer.MAX_VALUE;
		for(int i = 0; i < pathFinal.size(); i++)
			path.add(pathFinal.get(i));
		return graph.distance(path);
	}
	
	
	//renvoie la plus courte distance entre 2 noeud
	@Override
	public int distance(String debut, String fin) {
		if(!calcule) algo(debut);
		return d.get(fin);
	}
	
	/*
	private boolean ExistChemin(String u, String v):
	    int n = graph.getNbNoeuds(); 
	    LinkedList<String> file = new LinkedList<>();
	    boolean[] visites = new boolean[n];
	    visites = Arrays.fill(false);
	    file.addLast(u);
	    String courant;
	    while(file.size() > 0){
	        courant = file.pull();
	        visites[courant] = true;
	        for(String i : graph.getLabels(){
	            if (graph.aArc(courant, i) && visites[i] == false){
	                file.addLast(i);
	                visites[i] = true;
	            }
	
	            else if (graph.aArc(courant, i) && i == v:
	                return True
	        }
	    } 
	    return False
                            
/*
	def estCycle(matriceAdj):
	    n = len(matriceAdj)
	    for i in range(n):
	        if ExistChemin(matriceAdj, i, i) == True:
	            return True
	    return False


		 */

	@Override
	public boolean estOK() {
		
		
		return true;
	}
}
