package graph;

public class Main {

    public static void println(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        int N = 11;
        int[][] edges = {{0,1},{0,2},{1,2},{2,3},{3,4},{4,5},{5,6},{6,7},{7,8},{8,9},{9,0}};
        int[] weights = {1,1,2,3,4,5,6,7,8,9,0};

        Graph g = new AdjacencyListsGraph(N, false);
        for (int i = 0; i < edges.length; i++)
            g.addEdge(edges[i][0], edges[i][1], weights[i]);

        println("Adjacency Lists:\n" + g);

        g.removeEdge(0, 1);
        g.removeEdge(0, 2);
        g.removeEdge(1, 2);
        g.removeEdge(2, 3);
        g.removeEdge(3, 4);
        g.removeEdge(4, 5);
        g.removeEdge(5, 6);
        g.removeEdge(6, 7);
        g.removeEdge(7, 8);
        g.removeEdge(8, 9);
        g.removeEdge(9, 0);
        println("The graph after some edge removals:\n" + g);
        
        //println("Adjacency Matrix representation:\n" + g.toAltGraphRepr(new AdjacencyMatrixGraph(g.num_nodes, g.isDirected())));
        //Graph h = g.toAltGraphRepr(new LinkedEdgesGraph(g.num_nodes, g.isDirected()));
        //println("List of Edges representation:\n" + h);
        //println("Number of Connected Components: " + ConnectedComponents.getComponents(h.num_nodes, ((LinkedEdgesGraph) h).getEdges()));

    }
}