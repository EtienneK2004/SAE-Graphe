package pcc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import graph.IGraph;

public class PCCDijkstra implements IPCC{
	
	
	private class Node {
		public String name;
		public Node previous_node;
		public int total_value;
		public boolean closed;
		 
		public Node(String name) {
			this.name = name;
			this.total_value = Integer.MAX_VALUE;
			this.previous_node = null;
			this.closed = false;
		}
	}
	
	
	private Map<String, Node> nodes;	
	IGraph graph = null;
	private boolean calcule = false;
	
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
	public void Dijkstra(Node current_node) {
		//System.out.println("Current:" + current_node.name);
		current_node.closed = true;
		
		int minimum_value = Integer.MAX_VALUE;
		Node minimum_node = null;
		
		if(graph.dOut(current_node.name) == 0) {
			for(Map.Entry<String, Node> key_value : nodes.entrySet()) {
				Node node = key_value.getValue();
				if((node.total_value < minimum_value) && (!node.closed)){
					minimum_value = node.total_value;
					minimum_node = node;
				}
			}
			
			if(minimum_node != null) {
				Dijkstra(minimum_node);
			}
			
			return;
		}
		
		for(String name : graph.getSucc(current_node.name)) {

			Node node = nodes.get(name);
			
			int arc_value = graph.getValeur(current_node.name, name);
			
			int value_test = current_node.total_value + arc_value;
			
			if(value_test < node.total_value) {
				node.total_value = value_test;
				node.previous_node = current_node;
			}
			
			
			if(node.total_value < minimum_value) {
				minimum_value = node.total_value;
				minimum_node = node;
			}
		}

		
		for(Map.Entry<String, Node> key_value : nodes.entrySet()) {
			Node node = key_value.getValue();
			if((node.total_value < minimum_node.total_value) && (!node.closed))	{
				minimum_node = node;
			}
		}

		//DisplayNodes();
		
		if(!minimum_node.closed) {
			Dijkstra(minimum_node);
		}
	}
	
	/**
	 * Affiche le resultat de l'algorithme.
	 */
	/*public void DisplayNodes() {
		for(String name : graph.getLabels()) {
			Node node = nodes.get(name);
			// (node.closed ? "closed" : "opened")  c'est pour savoir si il est d�j� all� dessus
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
	
	/**
	 * Effectue l'agorithme de Dijkstra en prennant des String.
	 * 
	 * @param begin_node , noeud de depart pour faire l'algorithme.
	 * @param end_node , noeud d'arrivee.
	 */
	public void algo(String begin_node, String end_node) {
		//System.out.println("Debut de l'algorithme de Dijkstra.");
		nodes.clear();
		
		for(String name : graph.getLabels()) {
			nodes.put(name, new Node(name));
		}
		
		Node node = nodes.get(begin_node);
		node.total_value = 0;
		
		Dijkstra(node);		
		
		//System.out.println("Le plus court chemin partant du noeud \"" + begin_node + "\" vers le noeud \"" + end_node + "\" est : " + path);
		//System.out.println();
		calcule = true;
	}

	@Override
	public int distance(String entrant, String sortant) {
		algo(entrant, sortant);
		if(!calcule) algo(entrant, sortant);
		Node node = nodes.get(sortant);
		return node.total_value;
	}

	@Override
	public int chemin(String entrant, String sortant, ArrayList<String> path) {
		algo(entrant, sortant);
		if(!calcule) algo(entrant, sortant);
		
		
		Node loop_node = nodes.get(sortant);
		while(loop_node != null) {
			path.add(loop_node.name);
			loop_node = loop_node.previous_node;
		}
		
		ArrayList<String> pathFinal = new ArrayList<String>();
		for(int i = path.size() - 1; i >= 0; --i)
			pathFinal.add(path.get(i));
		path.clear();
		for(int i = 0; i < pathFinal.size(); i++)
			path.add(pathFinal.get(i));
		
		
		return distance(entrant, sortant);
	}
		
}
