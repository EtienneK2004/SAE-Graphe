package graph;

import java.util.HashMap;
import java.util.Map;

public class Toto {
	
	
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
	public Toto(IGraph graph) {
		this.graph = graph;
		this.nodes = new HashMap<>();
	}
	
	
	public void Initialize(Node current_node) {
		System.out.println("Current:" + current_node.name);
		current_node.closed = true;
		
		int minimum_value = Integer.MAX_VALUE;
		Node minimum_node = null;
		
		if(graph.dOut(current_node.name) == 0)
		{
			for(Map.Entry<String, Node> key_value : nodes.entrySet()) {
				Node node = key_value.getValue();
				if((node.total_value < minimum_value) && (!node.closed)){
					minimum_value = node.total_value;
					minimum_node = node;
				}
			}
			
			if(minimum_node != null)
			{
				Initialize(minimum_node);
			}
			
			return;
		}
		
		for(String name : graph.getSucc(current_node.name)) {

			Node node = nodes.get(name);
			
			int arc_value = graph.getValeur(current_node.name, name);
			
			int value_test = current_node.total_value + arc_value;
			
			if(value_test < node.total_value)
			{
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

		DisplayNodes();
		
		if(!minimum_node.closed) {
			Initialize(minimum_node);
		}
		else {
			
		}
	}
	
	public void DisplayNodes()
	{
		for(String name : graph.getLabels())
		{
			Node node = nodes.get(name);
			System.out.println("[" + node.name + "] -> " + node.total_value + "("+ (node.previous_node != null ? node.previous_node.name : "null") +
					") " + (node.closed ? "closed" : "opened"));
			
		}
		System.out.println();
	}
	
	
	public void test(String begin_node, String end_node) {
		nodes.clear();
		
		for(String name : graph.getLabels())
		{
			nodes.put(name, new Node(name));
		}
		
		Node node = nodes.get(begin_node);
		node.total_value = 0;
		
		Initialize(node);
		
		String path = "";

		Node loop_node = nodes.get(end_node);
		boolean bFirst = true;
		while(loop_node != null)
		{
			path = loop_node.name + (bFirst ? "" : "-") + path;
			loop_node = loop_node.previous_node;
			bFirst = false;
		}
		
		System.out.println(path);
		
	}
	
	
}
