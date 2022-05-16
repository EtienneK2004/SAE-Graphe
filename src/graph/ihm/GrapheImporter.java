package graph.ihm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import graph.IGraph;
import graph.types.GrapheLA;
import pcc.*;

public class GrapheImporter {
	
	public static void main(String[] args) throws NumberFormatException, FileNotFoundException, IOException {
		String fileName = "graphes/ac/g-10-1.txt";
		System.out.println();
		/*if (args.length != 1) 
			fileName = "graphe1.txt";
		else fileName = args[0];*/
		System.out.println("filename = "+ fileName);
		Arc df = new Arc();
		IGraph g = importer(fileName, df);
		System.out.print(g);
		System.out.println("debut fin : "+ df.getSource() + " ==> "+ df.getDestination());
		afficherExo31();
		verifierGraphes();
	}
	
	/**
	 * Affiche le résultat sous le format attendu
	 * @param chemin
	 * @return
	 */
	public static String cheminToString(List<String> chemin) {
		StringBuilder sb = new StringBuilder();
		for (String n : chemin)
			sb.append(n + " ");
		return sb.toString();
	}
	
	public static String[] getTab(int nbNoeuds) {
		String[] tab = new String[nbNoeuds];
		for(int i = 0; i < nbNoeuds; ++i) {
			Integer tmp = i + 1;
			tab[i] = tmp.toString();
		}
		return tab;
	}
	
	/**
	 * Affiche le résultat obtenu avec Dijktra, exercice 3.1
	 */
	static void afficherExo31() {
		// ci dessous voici comme afficher la sortie attendue
		// pour l'exo 3.1 du poly de maths
		// en supposant que A = 1, B = 2, ... I = 9
		// il aurait été plus pratique d'ailleurs de la mettre dans un fichier texte
		// comme pour les autres graphes
		
		int nbNoeuds = 9;
		IGraph g = new GrapheLA(getTab(nbNoeuds));
		System.out.println("toto");
		g.ajouterArc("1","3",2);
		g.ajouterArc("1","4",1);
		g.ajouterArc("2","7",3);
		g.ajouterArc("3","8",2);
		g.ajouterArc("4","2",3);
		g.ajouterArc("4","3",5);
		g.ajouterArc("4","5",3);
		g.ajouterArc("5","3",1);
		g.ajouterArc("5","7",3);
		g.ajouterArc("5","8",7);
		g.ajouterArc("7","2",2);
		g.ajouterArc("7","6",1);
		g.ajouterArc("8","6",4);
		g.ajouterArc("8","7",2);
		g.ajouterArc("9","8",10);
		
		IPCC pcc = new PCCDijkstra(g);
		ArrayList<String> path = new ArrayList<>();
		int distance = pcc.chemin(convertToString(1), convertToString(6), path);
		
		System.out.println("Dijkstra");
		System.out.println(distance);
		for (String s : path)
			System.out.print(s + " ");
		System.out.println();
	}
	
	private static String convertToString(Integer i) {
		return i.toString();
	}
	
	public static boolean comparer(String fichierGraphe, String fichierReponse, int index)
					throws NumberFormatException, IOException {
		IPCC algo;
		ArrayList<String> cheminPossible = new ArrayList<>();
		ArrayList<String> cheminCalcule = new ArrayList<>();
		Arc df = new Arc();
		IGraph g;
		g = GrapheImporter.importer(fichierGraphe, df);
		if(index == 0)
			algo = new PCCDijkstra(g);
		else
			algo = new PCCBellman(g);
		int distanceCalculee = algo.chemin(convertToString(df.getSource()), convertToString(df.getDestination()), cheminCalcule);
		int distanceAttendue = GrapheImporter.importerReponse(fichierReponse, cheminPossible);
		System.out.println(fichierGraphe + " vs " +  fichierReponse);
		System.out.println("Chemin possible : "+ cheminToString(cheminPossible));
		System.out.println("Chemin calcule : "+ cheminToString(cheminCalcule));
		System.out.println("Distance attendue : " + distanceAttendue);
		System.out.println("Distance calculee : " + distanceCalculee);
		if (distanceCalculee != distanceAttendue)
			return false;
		int distanceVerifiee = g.distance(cheminCalcule);
		System.out.println("Distance verifiee "+ distanceVerifiee);
		return true;
	}
	
	public static void verifierGraphes() throws FileNotFoundException {
		IGraph g;
		Arc df = new Arc(); 
		String dirStr = System.getProperty("user.dir")+ "\\graphes\\sc";
		System.out.println("Working Directory = " + dirStr);
		File dir = new File(dirStr);
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		      System.out.println(child);
		      g = importer(child, df);
		      System.out.println(g.getNbNoeuds() + " sommets");
		      System.out.println("debut et fin du chemin à trouver : "+ df.getSource()+ " ==> "+ df.getDestination()+"\n");
		    }
		  } else {
		    System.out.println("Erreur : "+ dirStr + " n'est pas un réperoire");
		  }
	}
	
	/**
	 * Retourne l'arc correctement initialisé
	 * @param string
	 * @return
	 */
	public static Arc parse(String string) {
		String[] parts = string.split(" ");
		int source, valuation, destination;
		try {  
			source = Integer.parseInt(parts[0]);
			valuation = Integer.parseInt(parts[1]);
			destination = Integer.parseInt(parts[2]);
		} catch (Exception e) {
			throw new IllegalArgumentException(string + " n'est pas un arc");
		}
		Arc a = new Arc(source, valuation, destination);
		return a;
	}
	
	/**
	 * Retourne le graphe initialisé
	 * @param file
	 * @param df
	 * @return
	 * @throws FileNotFoundException
	 */
	private static IGraph importer(File file, Arc df) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		String line;
		IGraph g;
		if (! sc.hasNextLine()) {
			sc.close();
    		throw new IllegalArgumentException("Pas de graphe dans "+ file);
		}
		line = sc.nextLine();
		int nbNodes = Integer.parseInt(line.trim());
		g = new GrapheLA(getTab(nbNodes));
		Arc a;
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			a = parse(line);
			if (sc.hasNextLine())
				g.ajouterArc(convertToString(a.getSource()), convertToString(a.getDestination()), a.getValuation());
			else {// la derniere ligne n'est pas un arc mais le debut et la fin du chemin Ã  trouver
				df.set(a);
			}
		}
		sc.close();
		return g;		
	}
	
	/**
	 * Retourne le graphe initialisé
	 * @param filepath
	 * @param df
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static IGraph importer(String filepath, Arc df) 
								throws  NumberFormatException, IOException, FileNotFoundException {
		File file = new File(filepath);
		return importer(file, df);
      }
	
	/**
	 * Retourne la distance et le chemin
	 * @param filePath
	 * @param[inout] chemin
	 * @return distance
	 * @throws FileNotFoundException
	 */
	public static int importerReponse(String filePath, List<String> chemin) throws FileNotFoundException {
		File file = new File(filePath);
		Scanner sc = new Scanner(file);
		String line;
		if (! sc.hasNextLine()) {
			sc.close();
    		throw new IllegalArgumentException("Pas de reponse dans "+ file);
		}
		line = sc.nextLine(); // nom de l'algo recommandé
		if (! sc.hasNextLine()) {
			sc.close();
    		return Integer.MAX_VALUE;
		}
		line = sc.nextLine(); // distance attendue
		int distance = Integer.parseInt(line.trim());
		line = sc.nextLine(); // chemin
		String[] noeuds = line.split(" ");
		for(String s : noeuds) {
			Integer tmp = Integer.parseInt(s);
			chemin.add(tmp.toString());
		}
		return distance;
	}
}
