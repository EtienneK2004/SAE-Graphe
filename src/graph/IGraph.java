package graph;

import java.util.ArrayList;

public interface IGraph {
	
	/**
	 * Retourne le tableau des successeurs
	 * 
	 * @param predecesseur
	 * @return tab successeurs
	 */
	String[] getSucc(String predecesseur);
	
	/**
	 * Retourne les labels du graphe
	 * 
	 * @return tab labels
	 */
	String[] getLabels();
	
	/**
     * Retourne le nombre de noeuds du graphe
     *
     * @return nombre de noeuds du graphe
     */
	int getNbNoeuds();
	
	/**
     * Verifie si le noeud est dans le graphe
     *
     * @param label Noeud a verifier
     * @return true si le noeud est dans le graphe, false sinon
     */
	boolean estNoeudOK(String label);
	
	/**
     * Verifie si les deux noeuds sont dans le graphe
     *
     * @param n1, n2 Noeuds a verifier
     * @return true si le noeud est dans le graphe, false sinon
     */
	boolean estArcOK(String n1, String n2);
	
	/**
     * Ajoute un arc entre deux noeuds
     *
     * @param entrant Noeud d'entree
     * @param sortant Noeud de sortie
     * @param valeur Pondération de l'arc
     */
	void ajouterArc(String entrant, String sortant, int valeur);
	
	/**
     * Retourne si l'arc entre deux noeuds est present dans le graphe
     *
     * @param entrant Noeud d'entree
     * @param sortant Noeud de sortie
     * @return true si l'arc entre deux noeuds est present dans le graphe, false sinon
     */
	boolean aArc(String entrant, String sortant);
	
	/**
     * Retourne le nombre de successeurs d'un noeud
     *
     * @param entrant Noeud d'entree
     * @return Nombre de successeurs d'un noeud
     */
	int dOut(String entrant);

	/**
     * Retourne le nombre de predecesseurs d'un noeud
     *
     * @param sortant Noeud de sortie
     * @return Nombre de predecesseurs d'un noeud
     */
	int dIn(String sortant);
	
	/**
	 * Permet d'obtenir la ponderation d'un arc, throw une RunTimeException si l'arc n'existe pas
	 * @param n1 Noeud entrant de l'arc
	 * @param n2 Noeud sortant de l'arc
	 * @return La ponderation
	 */
	int getValeur(String n1, String n2);

	
	int distance(ArrayList<String> cheminCalcule);

}
