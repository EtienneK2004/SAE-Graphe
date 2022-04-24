package graph;

import java.util.ArrayList;

public class PCCMatteo {
	private static ArrayList<String> tabAlgo = new ArrayList<>();
	private static ArrayList<String> bad = new ArrayList<>();
	
	public static void test(IGraph g, String premier, String dernier, String[] NOEUDS) {
		tabAlgo.add(premier);
		while(tabAlgo.get(tabAlgo.size()-1) != dernier) {
			if(g.dOut(tabAlgo.get(tabAlgo.size()-1)) != 0) {
				if(getProchain(tabAlgo.get(tabAlgo.size()-1),g) != null)
					tabAlgo.add(getProchain(tabAlgo.get(tabAlgo.size()-1),g));
			}
			else {
				bad.add(tabAlgo.get(tabAlgo.size()-1));
				tabAlgo.remove(tabAlgo.size()-1);				
			}
		}
		System.out.println("last");
		for(String s:tabAlgo)
			System.out.print(s+", ");
		System.out.println();
		int total = 0;
		for(int i = 0; i < tabAlgo.size() - 1; i++) {
			total += g.getValeur(tabAlgo.get(i), tabAlgo.get(i + 1));
		}
		System.out.println("total :" + total);
	}
	
	private static String getProchain(String predescesseur, IGraph g) {
		String min = g.getSucc(predescesseur)[0];
		for(String s:g.getSucc(predescesseur)) {
			boolean possible = true;
			for(String sbad : bad)
				if(s == sbad)
					possible = false;
			if(g.getValeur(predescesseur, s) < g.getValeur(predescesseur, min) && possible)
				min = s;
		}
		for(String sbad : bad)
			if(min == sbad) {
				bad.add(tabAlgo.get(tabAlgo.size()-1));
				tabAlgo.remove(tabAlgo.size()-1);
				return null;
			}
		return min;		
	}
}
