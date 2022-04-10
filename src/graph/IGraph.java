package graph;

public interface IGraph {

	int getNbNoeuds();

	boolean estNoeudOK(String label);

	boolean estArcOK(String n1, String n2);

	void ajouterArc(String label1, String label2, int valeur);

	boolean aArc(String label1, String label2);

	int dOut(String label);

	int dIn(String label);

	int getValeur(String n1, String n2);

}
