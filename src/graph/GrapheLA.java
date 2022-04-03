package graph;

import java.util.ArrayList;

public class GrapheLA {
    private final ArrayList<Integer>[] listeAdj;
    private int nbNoeuds;

    public GrapheLA(int nbNoeuds) {
        this.nbNoeuds = nbNoeuds;
        this.listeAdj = new ArrayList[nbNoeuds];

        for (int i = 0; i < nbNoeuds; i++) {
            this.listeAdj[i] = new ArrayList<>();
        }
    }

    /**
     * V�rifie si le noeud est dans le graphe
     *
     * @param arc Arc � v�rifier
     * @return true si le noeud est dans le graphe, false sinon
     */
    private boolean checkArc(int arc) {
        return arc - 1 < this.nbNoeuds && arc - 1 >= 0;
    }

    /**
     * Retourne le nombre de noeuds du graphe
     *
     * @return nombre de noeuds du graphe
     */
    public int getNbNoeuds() {
        return nbNoeuds;
    }

    /**
     * Ajoute un arc entre deux noeuds
     *
     * @param entrant Noeud d'entr�e
     * @param sortant Noeud de sortie
     */
    public void ajouterArc(int entrant, int sortant) {
        if (!(checkArc(entrant) && checkArc(sortant))) return;

        this.listeAdj[entrant - 1].add(sortant);
    }

    /**
     * Retourne si l'arc entre deux noeuds est pr�sent dans le graphe
     *
     * @param entrant Noeud d'entr�e
     * @param sortant Noeud de sortie
     * @return true si l'arc entre deux noeuds est pr�sent dans le graphe, false sinon
     */
    public boolean aArc(int entrant, int sortant) {
        if (!(checkArc(entrant) && checkArc(sortant))) return false;

        return this.listeAdj[entrant - 1].contains(sortant);
    }

    /**
     * Retourne le nombre de successeurs d'un noeud
     *
     * @param entrant Noeud d'entr�e
     * @return Nombre de successeurs d'un noeud
     */
    public int dOut(int entrant) {
        if (!checkArc(entrant)) return 0;

        return this.listeAdj[entrant - 1].size();
    }

    /**
     * Retourne le nombre de pr�d�cesseurs d'un noeud
     *
     * @param sortant Noeud de sortie
     * @return Nombre de pr�d�cesseurs d'un noeud
     */
    public int dIn(int sortant) {
        if (!checkArc(sortant)) return 0;

        int nbArcs = 0;
        for (int i = 0; i < this.nbNoeuds; i++) {
            if (this.listeAdj[i].contains(sortant)) {
                nbArcs++;
            }
        }

        return nbArcs;
    }

    /**
     * Retourne la liste d'adjacence du graphe sous chaine de caract�res
     *
     * @return Liste d'adjacence du graphe
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.nbNoeuds; i++) {
            sb.append(i + 1).append(" -> ");
            for (int value : this.listeAdj[i]) {
                sb.append(value).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
