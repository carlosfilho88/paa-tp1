package graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
    private static Scanner in;
    public static Map<Integer, Integer> vMap = new HashMap<Integer, Integer>();
    public static Map<Integer, Integer> ivMap = new HashMap<Integer, Integer>();

	public static void println(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> vertexes = new ArrayList<Integer>();
        ArrayList<Edge> edges = new ArrayList<Edge>();
        BufferedReader buffer = new BufferedReader(new FileReader("src/jose.txt"));
        Integer node_id = 0,indexer = 0;
        
        in = new Scanner(buffer);
        
        while(in.hasNextLine()) {
        	node_id = in.nextInt();
        	if(!vMap.containsKey(node_id)) {
        		vMap.put(node_id, indexer);
        		if(!ivMap.containsKey(indexer))
        			ivMap.put(indexer, node_id);
        		indexer++;
        	}
	    	node_id = in.nextInt();
	    	if(!vMap.containsKey(node_id)){
        		vMap.put(node_id, indexer);
        		if(!ivMap.containsKey(indexer))
        			ivMap.put(indexer, node_id);
        		indexer++;
        	}
        }
        
        buffer = new BufferedReader(new FileReader("src/jose.txt"));
        in = new Scanner(buffer);
        
        while(in.hasNextLine()) {
        	edges.add(new Edge(vMap.get(in.nextInt()), vMap.get(in.nextInt())));
        }
        
    	vertexes.addAll(vMap.values());
        
        Graph g = new AdjacencyListsGraph(vertexes, edges, false);
        for (Iterator<Edge> iterator = edges.iterator(); iterator.hasNext();) {
			Edge edge = (Edge) iterator.next();
			g.addEdge(edge.source(), edge.target());
		}
        	
        println("Adjacency Lists:\n" + g);
        //g.removeEdge(vMap.get(1), vMap.get(2));
        //println("The graph after some edge removals:\n" + g);
        
        Betweeness.BFS(g, vMap.get(1));
        
        //println("Adjacency Matrix representation:\n" + g.toAltGraphRepr(new AdjacencyMatrixGraph(g.num_nodes, g.isDirected())));
        //Graph h = g.toAltGraphRepr(new LinkedEdgesGraph(g.num_nodes, g.isDirected()));
        //println("List of Edges representation:\n" + h);
        //println("Number of Connected Components: " + ConnectedComponents.getComponents(h.num_nodes, ((LinkedEdgesGraph) h).getEdges()));

    }
}
