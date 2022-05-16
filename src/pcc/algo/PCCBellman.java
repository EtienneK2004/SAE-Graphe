package pcc.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import graph.IGraph;
import pcc.IPCC;

public class PCCBellman implements IPCC{
	private static final int INFINI = Integer.MAX_VALUE;
	private IGraph graph;
	private HashMap<String, Integer> d;       //distances du point de départ
	private HashMap<String, String> p;    //Prédécesseur optimal pour chaque point

	
	
	
	
	
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

	
	/*
	 * @brief renvoie le plus court chemin entre 2 noeud
	 * @param debut, noeud de départ
	 * @param fin, noeud d'arrivée
	 * @param path, sera remplacé par le chemin du plus court chemin entre les 2 noeuds
	 * @return la distance totale du chemin
	 */
	@Override
	public int chemin(String debut, String fin, ArrayList<String> path) {
		assert(estOK());
		algo(debut);

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
	
	
	/*
	 * @brief Calcule la plus courte distance entre 2 noeuds
	 * @param debut, noeud de départ
	 * @param fin, noeud d'arrivée
	 * @return la plus courte distance entre les 2 noeuds
	 */
	@Override
	public int distance(String debut, String fin) {
		algo(debut);
		return d.get(fin);
	}
	
	/*
	 * @brief Determine si un chemin est possible entre 2 noeuds
	 * @param u, noeud de départ
	 * @param v, noeud d'arrivée
	 * @return true si un chemin existe, false sinon
	 */
	private boolean ExistChemin(String u, String v) {
	    LinkedList<String> file = new LinkedList<>();
	    HashMap<String, Boolean> visites = new HashMap<>();
	    for(String label : graph.getLabels()) {
	    	visites.put(label, false);
	    }
	    file.addLast(u);
	    String courant;
	    while(file.size() > 0){
	        courant = file.removeFirst();
	        visites.put(courant, true);
	        for(String i : graph.getLabels()){
	            if (graph.aArc(courant, i) && visites.get(i) == false){
	                file.addLast(i);
	                visites.put(i, true);
	            }
	
	            else if (graph.aArc(courant, i) && i == v)
	                return true;
	        }
	    } 
	    return false;
	}
                            
	/*
	 * @brief Determine si le graphe est cyclique
	 * @return true si le graphe possède un cycle, false sinon
	 */
	private boolean estCycle() {
	    for(String i : graph.getLabels())
	        if (ExistChemin(i, i) == true)
	            return true;
	    return false;
	}


		 
	/*
	 * @brief Determine si le graphe est compatible avec l'algorithme
	 * @return true si il est compatible, false sinon
	 */
	@Override
	public boolean estOK() {
		
		
		return !estCycle();
	}
}
