package graph;

import java.util.List;

public class AdjacencyMatrixGraph extends Graph {

    private int[][] edges;

    public AdjacencyMatrixGraph(List<Integer> v, List<Edge> e, boolean d) {
        super(v, e, d);

        edges = new int[num_nodes][num_nodes];
        for (int i = 0; i < num_nodes; i++)
            for (int j = 0; j < num_nodes; j++)
                edges[i][j] = Integer.MAX_VALUE;
    }
    
    public void addEdge(Integer source, Integer target) {
        edges[source][target] = 1;
        if (!directed)
            edges[target][source] = 1;
    }

    public void addEdge(Integer source, Integer target, int weight) {
        edges[source][target] = weight;
        if (!directed)
            edges[target][source] = weight;
    }

    public int removeEdge(Integer source, Integer target) {
    	int weight = edges[source][target];
        edges[source][target] = Integer.MAX_VALUE;

        if (!directed)
            edges[target][source] = Integer.MAX_VALUE;

        return weight;
    }

    public void removeAllEdges() {
        for (int i = 0; i < num_nodes; i++)
            for (int j = 0; j < num_nodes; j++)
                edges[i][j] = Integer.MAX_VALUE;
    }

    public int weightOfEdge(Integer source, Integer target) {
        return edges[source][target];
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < num_nodes; i++) {
            for (int j = 0; j < num_nodes; j++) {
                if (edges[i][j] == Integer.MAX_VALUE)
                    s += " -" + "\t";
                else
                    s += " " + edges[i][j] + "\t";
            }
            s += "\n";
        }

        return s;
    }

    public Graph toAltGraphRepr(Graph g) {
        return g;
    }

}