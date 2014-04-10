package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void println(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        Integer count = 0;
        Map<Integer, Integer> vertexes = new HashMap<Integer, Integer>();
        vertexes.put(1, count++);
        vertexes.put(2, count++);
        vertexes.put(3, count++);
        vertexes.put(4, count++);
        vertexes.put(5, count++);
        vertexes.put(6, count++);
        vertexes.put(7, count++);
        vertexes.put(8, count++);
        vertexes.put(9, count++);
        vertexes.put(10, count++);
        ArrayList<Edge> edges = new ArrayList<Edge>();
        edges.add(new Edge(vertexes.get(1), vertexes.get(2)));
        edges.add(new Edge(vertexes.get(1), vertexes.get(3)));
        edges.add(new Edge(vertexes.get(1), vertexes.get(4)));
        edges.add(new Edge(vertexes.get(2), vertexes.get(5)));
        edges.add(new Edge(vertexes.get(3), vertexes.get(6)));
        edges.add(new Edge(vertexes.get(3), vertexes.get(7)));
        edges.add(new Edge(vertexes.get(4), vertexes.get(8)));
        edges.add(new Edge(vertexes.get(5), vertexes.get(9)));
        edges.add(new Edge(vertexes.get(6), vertexes.get(10)));
        
        Graph g = new AdjacencyListsGraph(new ArrayList<Integer>(vertexes.values()), edges, false);
        for (int i = 0; i < edges.size(); i++)
            g.addEdge(edges.get(i).source(), edges.get(i).target());
    	
    	/*ArrayList<Vertex> vertexes = new ArrayList<Vertex>();
        vertexes.add(new Vertex(0));
        vertexes.add(new Vertex(1));
        vertexes.add(new Vertex(2));
        vertexes.add(new Vertex(3));
        vertexes.add(new Vertex(4));
        vertexes.add(new Vertex(5));
        ArrayList<Edge> edges = new ArrayList<Edge>();
        edges.add(new Edge(vertexes.get(5), vertexes.get(3)));
        edges.add(new Edge(vertexes.get(3), vertexes.get(2)));
        edges.add(new Edge(vertexes.get(3), vertexes.get(4)));
        edges.add(new Edge(vertexes.get(4), vertexes.get(1)));
        edges.add(new Edge(vertexes.get(4), vertexes.get(0)));
        edges.add(new Edge(vertexes.get(1), vertexes.get(0)));
        
        Graph g = new AdjacencyListsGraph(vertexes, edges, false);
        for (int i = 0; i < edges.size(); i++)
            g.addEdge(edges.get(i).source(), edges.get(i).target());*/

        //println("Adjacency Lists:\n" + g);
        //g.removeEdge(vertexes.get(0), vertexes.get(1));
        //println("The graph after some edge removals:\n" + g);
        
        Betweeness.BFS(g, vertexes.get(10));
        
        //println("Adjacency Matrix representation:\n" + g.toAltGraphRepr(new AdjacencyMatrixGraph(g.num_nodes, g.isDirected())));
        //Graph h = g.toAltGraphRepr(new LinkedEdgesGraph(g.num_nodes, g.isDirected()));
        //println("List of Edges representation:\n" + h);
        //println("Number of Connected Components: " + ConnectedComponents.getComponents(h.num_nodes, ((LinkedEdgesGraph) h).getEdges()));

    }
}