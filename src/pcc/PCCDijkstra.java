package pcc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import graph.IGraph;

public class PCCDijkstra implements IPCC{
	
	
	private class Node {
		public String name;
		public Node previous_node;
		public int total_value;
		 
		public Node(String name) {
			this.name = name;
			this.total_value = Integer.MAX_VALUE;
			this.previous_node = null;
		}
	}
	
	private class ArcNegatifExeption extends RuntimeException{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	}
	
	
	private Map<String, Node> nodes;	
	IGraph graph = null;
	
	/**
	 * Cree l'objet PCCDijkstra qui pourra etre utilise pour n'importe quel noeud d'un meme graphe.
	 * @param graph , graphe que l'on veut etudier.
	 */
	public PCCDijkstra(IGraph graph) {
		this.graph = graph;
		this.nodes = new HashMap<>();
	}
	
	/**
	 *  C'est la fonction recursive  qui compose l'algorithme.
	 * 
	 * @param current_node , noeud qui va etre etuidie. Pour le premier appel, il faut mettre le noeud de depart.
	 */
	public void Dijkstra() {
		LinkedList<Node> Q = new LinkedList<>();
		Node tmp = null;
		for(String name : nodes.keySet())
			Q.add(nodes.get(name));
		boolean fin = false;
		while(Q.size()>0 && !fin) {
			tmp = Trouve_min(Q);
			if(tmp == null) {
				fin = true;
				break;
			}
			Q.remove(tmp);
			
			for(String lab : graph.getSucc(tmp.name)) {
				maj_distance(tmp.name, lab);
			}
		}
	}
	
	/**
	 * Affiche le resultat de l'algorithme.
	 */
	/*public void DisplayNodes() {
		for(String name : graph.getLabels()) {
			Node node = nodes.get(name);
			// (node.closed ? "closed" : "opened")  c'est pour savoir si il est déjà allé dessus
			System.out.print("[" + node.name + "] -> " + (node.total_value == Integer.MAX_VALUE ? "I" : node.total_value) + 
					"("+ (node.previous_node != null ? node.previous_node.name : " ") + ") ");
		}
		System.out.println();
	}*/
	
	/**
	 * Effectue l'agorithme de Dijkstra en prennant des entiers.
	 * 
	 * @param begin_node , noeud de depart pour faire l'algorithme.
	 * @param end_node , noeud d'arrivee.
	 */
	public void algo(Integer begin_node, Integer end_node) {
		algo(begin_node.toString(), end_node.toString());
	}
	
	
	private Node Trouve_min(List<Node> Q) {
		int dMin = Integer.MAX_VALUE;
		Node res = null;
		for(Node n : Q) {
			if (n.total_value < dMin) {
				dMin = n.total_value;
				res = n;
			}
		}
		return res;
	}
	
	private void maj_distance(String s1, String s2) {
	   if(nodes.get(s2).total_value > nodes.get(s1).total_value + graph.getValeur(s1, s2)) {
		   nodes.get(s2).total_value = nodes.get(s1).total_value + graph.getValeur(s1, s2);
		   nodes.get(s2).previous_node = nodes.get(s1);
	   }
	}
	
	
	/**
	 * Effectue l'agorithme de Dijkstra en prennant des String.
	 * 
	 * @param begin_node , noeud de depart pour faire l'algorithme.
	 * @param end_node , noeud d'arrivee.
	 */
	public void algo(String begin_node, String end_node) {
		//System.out.println("Debut de l'algorithme de Dijkstra.");
		if(!estOK())
			throw new ArcNegatifExeption();
		nodes.clear();
		
		for(String name : graph.getLabels()) {
			nodes.put(name, new Node(name));
		}
		
		for(String lab : nodes.keySet()) {
			nodes.get(lab).total_value = Integer.MAX_VALUE;
		}
		
		Node node = nodes.get(begin_node);
		node.total_value = 0;
		
		Dijkstra();		
		
		//System.out.println("Le plus court chemin partant du noeud \"" + begin_node + "\" vers le noeud \"" + end_node + "\" est : " + path);
		//System.out.println();
	}

	@Override
	public int distance(String entrant, String sortant) {
		algo(entrant, sortant);
		Node node = nodes.get(sortant);
		return node.total_value;
	}

	@Override
	public int chemin(String entrant, String sortant, ArrayList<String> path) {
		algo(entrant, sortant);
		
		
		Node loop_node = nodes.get(sortant);
		while(loop_node != null) {
			path.add(loop_node.name);
			loop_node = loop_node.previous_node;
		}
		
		ArrayList<String> pathFinal = new ArrayList<String>();
		for(int i = path.size() - 1; i >= 0; --i)
			pathFinal.add(path.get(i));
		path.clear();
		if(!(pathFinal.get(0).equals(entrant) && pathFinal.get(pathFinal.size()-1).equals(sortant)) )
			return Integer.MAX_VALUE;
		
		for(int i = 0; i < pathFinal.size(); i++)
			path.add(pathFinal.get(i));
		
		
		return graph.distance(path);
	}
	
	public boolean estOK() {
		for(Node node : nodes.values()) {
			for(String node2 : graph.getSucc(node.name)) {
				if(graph.aArc(node.name, node2))
						if(graph.getValeur(node.name, node2) < 0)
							return false;
			}
		}
		return true;
	}
		
}
