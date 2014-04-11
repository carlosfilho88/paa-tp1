package graph;

import java.util.List;

public abstract class Graph {
    
	protected List<Integer> vertexes;
	protected List<Edge> edges;
    protected int num_nodes;
    protected boolean directed;

    public Graph(List<Integer> vertexes, List<Edge> edges, boolean directed) {
      this.vertexes = vertexes;
      this.edges = edges;
      this.directed = directed;
      this.num_nodes = vertexes.size();
    }

    public List<Integer> getVertexes() {
      return vertexes;
    }

    public List<Edge> getEdges() {
      return edges;
    }

    public int getNumNodes() {
        return num_nodes;
    }

    public boolean isDirected() {
        return directed;
    }

    public abstract void addEdge(Integer source, Integer target);
    
    public abstract void addEdge(Integer source, Integer target, int weight);

    public void addEdge(EdgeInterface e) {
        addEdge(e.source(), e.target(), e.weight());
    }

    public abstract int removeEdge(Integer source, Integer target);

    public abstract void removeAllEdges();

    public abstract int weightOfEdge(Integer source, Integer target);

    public abstract Graph toAltGraphRepr(Graph G);

}