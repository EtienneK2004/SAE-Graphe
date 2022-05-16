package pcc;

import java.util.ArrayList;

public interface IPCC {
	/*
	 * @brief Calcule la plus courte distance entre 2 noeuds
	 * @param debut, noeud de départ
	 * @param fin, noeud d'arrivée
	 * @return la plus courte distance entre les 2 noeuds
	 */
	int distance(String entrant, String sortant);
	
	/*
	 * @brief Renvoie le plus court chemin entre 2 noeud
	 * @param debut, noeud de départ
	 * @param fin, noeud d'arrivée
	 * @param path, sera remplacé par le chemin du plus court chemin entre les 2 noeuds
	 * @return la distance totale du chemin
	 */
	int chemin(String entrant, String sortant, ArrayList<String> path);
	/*
	 * @brief Determine si le graphe est compatible avec l'algorithme
	 * @return true si il est compatible, false sinon
	 */
	boolean estOK();
}
