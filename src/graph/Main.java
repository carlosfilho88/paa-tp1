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
    private static BufferedReader buffer;
    private static FileReader file;
    public static Map<Integer, Integer> vMap = new HashMap<Integer, Integer>();
    public static Map<Integer, Integer> ivMap = new HashMap<Integer, Integer>();

    public static void main(String[] args) throws FileNotFoundException {
    	Integer node_id = 0,indexer = 0;
    	Betweeness betweeness;
        ArrayList<Integer> vertexes = new ArrayList<Integer>();
        ArrayList<Edge> edges = new ArrayList<Edge>();
        file = new FileReader("src/jose.txt");
        buffer = new BufferedReader(file);
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
        file = new FileReader("src/jose.txt");
        buffer = new BufferedReader(file);
        in = new Scanner(buffer);
        
        while(in.hasNextLine()) {
        	edges.add(new Edge(vMap.get(in.nextInt()), vMap.get(in.nextInt())));
        }
        
    	vertexes.addAll(vMap.values());
    	AdjacencyMatrixGraph g = new AdjacencyMatrixGraph(vertexes, edges, false);
        
        for (Iterator<Edge> iterator = edges.iterator(); iterator.hasNext();) {
			Edge edge = (Edge) iterator.next();
			g.addEdge(edge.source(), edge.target());
		}
        	
//        g.edges[5][6] = 0;
//        g.edges[6][5] = 0;
        
        betweeness = new Betweeness(g);
        System.out.println(g.getNumNodes());
        for (int i = 0; i <= g.getNumNodes(); i++) {
			if(Main.ivMap.containsKey(i)) {//i = id logico
//				betweeness.BFS(i);
        	System.out.println(betweeness.BFS(i).toString());
			}
        }
        System.out.println();
//        System.out.println(betweeness.toStringDists());
//        System.out.println(betweeness.toStringNPaths());
//        System.out.println("Adjacency Matrix representation:\n" + g);
        System.out.println(betweeness.toStringBetweenness());
        
    }
}
