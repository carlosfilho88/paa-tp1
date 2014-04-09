package graph;

public abstract class Graph {

    protected int num_nodes;
    protected boolean directed;

    public Graph(int nodes, boolean directed) {
        num_nodes = nodes;
        this.directed = directed;
    }

    public int getNumNodes() {
        return num_nodes;
    }

    public boolean isDirected() {
        return directed;
    }

    public abstract void addEdge(int source, int target, int weight);

    public void addEdge(EdgeInterface e) {
        addEdge(e.source(), e.target(), e.weight());
    }

    public abstract int removeEdge(int source, int target);

    public abstract void removeAllEdges();

    public abstract int weightOfEdge(int source, int target);

    public abstract Graph toAltGraphRepr(Graph G);

}