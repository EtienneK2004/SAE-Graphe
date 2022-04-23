package graph;


//test


import java.util.ArrayList;

public class PCCDijkstra {

	// getPred cassé pour grahLA
	// faire un if pour les noeuds qui ont une valeur != inf et prendre le plus petit comme prochain noeud
	// mettre dans noeud visite le plus petit de ceux là
	
	private static ArrayList<String> noeud_visites = new ArrayList<>();
	private static int[] distance_min;
	private static final int inf = Integer.MAX_VALUE;
	
	public static void test(IGraph g, String premier, String dernier) {
		noeud_visites.add(premier);
		distance_min = new int[g.getNbNoeuds()];
		String predescesseur[] = new String[g.getNbNoeuds()];
		for(int i = 0; i < distance_min.length; i++)
			distance_min[i] = inf;
		for(int i : distance_min)
			System.out.println(i);
		distance_min[getPlace(premier, g)] = 0;
		String noeud_Courant = null;
		while(!passe_partout(g)) {
			noeud_Courant = getNoeudMin(g, noeud_visites.get(noeud_visites.size()-1));
			noeud_visites.add(noeud_Courant);
			if(noeud_Courant.compareTo(dernier) == 0)
				break;
			for(String successeur : g.getSucc(noeud_Courant)) {
				if(noeud_Courant.compareTo("1") == 0)
					System.out.println(successeur);
				int distance = distance_min[getPlace(noeud_Courant, g)] + g.getValeur(noeud_Courant, successeur);
				if(distance < distance_min[getPlace(successeur, g)]) {
					distance_min[getPlace(successeur, g)] = distance;
					predescesseur[getPlace(successeur, g)] = noeud_Courant;
					System.out.println("test");
				}
			}
		}
		/*for(String s:noeud_visites)
			System.out.println("n visite " + s);
		for(int i : distance_min)
			System.out.println("dist min " +i);
		ArrayList<String> chemin = new ArrayList<>();
		if(g.dIn(dernier) != 0) {
			noeud_Courant = dernier;
			chemin.add(dernier);
			System.out.println(dernier + " " + g.dIn(noeud_Courant));
			while(g.dIn(noeud_Courant) > 0) {
				chemin.add(0, noeud_Courant);
				System.out.println(getPlace(noeud_Courant, g));
				noeud_Courant = predescesseur[getPlace(noeud_Courant, g)];
				System.out.println(noeud_Courant);
				for(String s:predescesseur)
					System.out.println("predecesseur " +s);
			}
		}
		for(String s : chemin)
			System.out.println(s + ", ");
		System.out.println(distance_min[getPlace(noeud_Courant, g)]);
		*/
	}
	
	private static int getPlace(String noeud, IGraph g) {
		for(int i = 0; i < g.getLabels().length;++i)
			if(noeud.compareTo(g.getLabels()[i]) == 0)
				return i;
		return -1;
	}
	
	private static String getNoeudMin(IGraph g, String predescesseur) {
		String min = g.getSucc(predescesseur)[0];
		for(String s:g.getSucc(predescesseur)) {
			if(g.getValeur(predescesseur, s) < g.getValeur(predescesseur, min))
				min = s;
		}
		return min;
	}

	private static boolean passe_partout(IGraph g) {
		for(String s : g.getLabels()) {
			boolean trouvee = false;
			for(String s2 : noeud_visites)
				if(s.compareTo(s2) == 0)
					trouvee = true;
			if(!trouvee)
				return false;
		}
		return true;
	}
}
