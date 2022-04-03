package graph;

import java.util.Arrays;

public class GrapheMA {

    private final boolean[][] matriceAdj;

    public GrapheMA(int nbNoeuds) {
        this.matriceAdj = new boolean[nbNoeuds][nbNoeuds];
        for (boolean[] ligne : matriceAdj)
            Arrays.fill(ligne, false);
    }

    /**
     * Retourne le nombre de noeuds du graphe
     * @return le nombre de noeuds du graphe
     */
    public int getNbNoeuds() {
        return matriceAdj.length;
    }

    /**
     * Ajoute un arc entre les noeuds entrant et sortant
     * @param entrant le noeud entrant
     * @param sortant le noeud sortant
     */
    public void ajouterArc(int entrant, int sortant) {
        matriceAdj[entrant - 1][sortant - 1] = true;
    }

    /**
     * Retourne si l'arc entrant-sortant existe
     * @param entrant le noeud entrant
     * @param sortant le noeud sortant
     * @return
     */
    public boolean aArc(int entrant, int sortant) {
        return matriceAdj[entrant - 1][sortant - 1];
    }

    /**
     * Retourne le nombre de successeurs d'un noeud
     * @param entrant le noeud entrant
     * @return le nombre de successeurs d'un noeud
     */
    public int dOut(int entrant) {
        int cpt = 0;

        for (boolean arc : matriceAdj[entrant - 1])
            if (arc) cpt++;

        return cpt;
    }

    /**
     * Retourne le nombre de prédécesseurs d'un noeud
     * @param sortant le noeud sortant
     * @return le nombre de prédécesseurs d'un noeud
     */
    public int dIn(int sortant) {
        int cpt = 0;

        for (int i = 0; i < getNbNoeuds(); i++)
            if (matriceAdj[i][sortant - 1]) cpt++;

        return cpt;
    }

    /**
     * Retourne la matrice d'adjacence du graphe sous forme de chaîne de caractères
     * @return Matrice d'adjacence du graphe
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (boolean[] ligne : matriceAdj) {
            for (boolean arc : ligne)
                sb.append(arc ? "1 " : "0 ");
            sb.append("\n");
        }

        return sb.toString();
    }
}



