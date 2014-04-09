package graph;

public class Main {

    public static void println(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        int N = 10;
        int[][] edges = {{0,1},{1,2},{2,3},{3,4},{4,5},{5,6},{6,7},{7,8},{8,9},{9,0}};
        int[] weights = {1,2,3,4,5,6,7,8,9,0};

        Graph g = new AdjacencyListsGraph(N, false);
        for (int i = 0; i < edges.length; i++)
            g.addEdge(edges[i][0], edges[i][1], weights[i]);

        println("Adjacency Lists:\n" + g);

        //g.removeEdge(0, 1);
        //g.removeEdge(8, 9);
        //println("The graph after some edge removals:\n" + g);
        
        println("Adjacency Matrix representation:\n" + g.toAltGraphRepr(new AdjacencyMatrixGraph(g.num_nodes, g.isDirected())));
        Graph h = g.toAltGraphRepr(new LinkedEdgesGraph(g.num_nodes, g.isDirected()));
        println("List of Edges representation:\n" + h);
        //println("Number of Connected Components: " + ConnectedComponents.getComponents(h.num_nodes, ((LinkedEdgesGraph) h).getEdges()));

    }
}