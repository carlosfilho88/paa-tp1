package graph;

public class AdjacencyMatrixGraph extends Graph {

    private int[][] edges;

    public AdjacencyMatrixGraph(int nodes, boolean directed) {
        super(nodes, directed);

        edges = new int[nodes][nodes];
        for (int i = 0; i < nodes; i++)
            for (int j = 0; j < nodes; j++)
                edges[i][j] = Integer.MAX_VALUE;
    }

    public void addEdge(int source, int target, int weight) {
        edges[source][target] = weight;
        if (!directed)
            edges[target][source] = weight;
    }

    public int removeEdge(int source, int target) {
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

    public int weightOfEdge(int source, int target) {
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