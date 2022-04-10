package graph;

public interface IGraph {
	
	/**
     * Retourne le nombre de noeuds du graphe
     *
     * @return nombre de noeuds du graphe
     */
	int getNbNoeuds();
	
	/**
     * Vérifie si le noeud est dans le graphe
     *
     * @param label Noeud à vérifier
     * @return true si le noeud est dans le graphe, false sinon
     */
	boolean estNoeudOK(String label);
	
	/**
     * Vérifie si les deux noeuds sont dans le graphe
     *
     * @param n1, n2 Noeuds à vérifier
     * @return true si le noeud est dans le graphe, false sinon
     */
	boolean estArcOK(String n1, String n2);
	
	/**
     * Ajoute un arc entre deux noeuds
     *
     * @param entrant Noeud d'entrée
     * @param sortant Noeud de sortie
     * @param valeur Pondération de l'arc
     */
	void ajouterArc(String entrant, String sortant, int valeur);
	
	/**
     * Retourne si l'arc entre deux noeuds est pr�sent dans le graphe
     *
     * @param entrant Noeud d'entrée
     * @param sortant Noeud de sortie
     * @return true si l'arc entre deux noeuds est présent dans le graphe, false sinon
     */
	boolean aArc(String entrant, String sortant);
	
	/**
     * Retourne le nombre de successeurs d'un noeud
     *
     * @param entrant Noeud d'entrée
     * @return Nombre de successeurs d'un noeud
     */
	int dOut(String entrant);

	/**
     * Retourne le nombre de prédécesseurs d'un noeud
     *
     * @param sortant Noeud de sortie
     * @return Nombre de prédécesseurs d'un noeud
     */
	int dIn(String sortant);
	
	/**
	 * Permet d'obtenir la pondération d'un arc, throw une RunTimeException si l'arc n'existe pas
	 * @param n1 Noeud entrant de l'arc
	 * @param n2 Noeud sortant de l'arc
	 * @return La pondération
	 */
	int getValeur(String n1, String n2);

}
