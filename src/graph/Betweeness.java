package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Betweeness {

    public static void BFS(Graph g, Integer source) {
    	boolean[] marked = new boolean[g.getNumNodes()];
	    int[] edgeTo = new int[g.getNumNodes()];
	    int[] distTo = new int[g.getNumNodes()];
    	    
    	Queue<AdjacencyListElement> queue = new LinkedList<AdjacencyListElement>();
    	ArrayList<AdjacencyListElement> adjList;
        AdjacencyListElement adjElement;
        
        for (int v = 0; v < g.getNumNodes(); v++) 
        	distTo[v] = Integer.MAX_VALUE;
        distTo[source] = 0;
        marked[source] = true;
        
        queue.add(new AdjacencyListElement(source));
        adjList = ((AdjacencyListsGraph)g).getAdjListOfNode(source);
        
        while (!queue.isEmpty()) {
            adjElement = queue.poll();
            adjList = ((AdjacencyListsGraph)g).getAdjListOfNode(adjElement.target);
            System.out.print(Main.ivMap.get(adjElement.target) + " ");
            for (Iterator<AdjacencyListElement> iter = adjList.iterator(); iter.hasNext();) {
        		AdjacencyListElement e = iter.next();
        		if (!marked[e.target]) {
                    edgeTo[e.target] = adjElement.target;
                    distTo[e.target] = distTo[adjElement.target] + 1;
                    marked[e.target] = true;
                    queue.add(e);
                }
                
            }
        }
    }
}