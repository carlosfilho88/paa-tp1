package graph;

import java.util.Iterator;
import java.util.LinkedList;

class AdjacencyListElement {

	public int target;
    public int weight;

    public AdjacencyListElement(int target, int weight) {
        this.target = target;
        this.weight = weight;
    }

    public String toString() {
        return "(" + target + "; " + (float) weight + ")";
    }
}

public class AdjacencyListsGraph extends Graph {

    private LinkedList<AdjacencyListElement>[] lists;

    @SuppressWarnings("unchecked")
    public AdjacencyListsGraph(int nodes, boolean directed) {
        super(nodes, directed);

        lists = new LinkedList[nodes];
        for (int i = 0; i < nodes; i++)
            lists[i] = new LinkedList<AdjacencyListElement>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see Graph#addEdge(int, int, double)
     * 
     * The new edge is added at the *front* of the adjacency list of the source
     * node. If the Graph is undirected, it is also added at the *front* of the
     * adjacency list of the target node.
     * 
     */
    public void addEdge(int source, int target, int weight) {

    }

    public int removeEdge(int source, int target) {
        // TODO: Fill in the body of this method.

        // TODO: remove next line --- added so that starter code compiles
        return 1;
    }

    public void removeAllEdges() {
        for (int i = 0; i < num_nodes; i++)
            lists[i].clear();
    }

    public int weightOfEdge(int source, int target) {
        // TODO: Fill in the body of this method.

        // TODO: remove next line --- added so that starter code compiles
        return Integer.MAX_VALUE;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < num_nodes; i++) {
            s += i + "->";

            for (Iterator<AdjacencyListElement> iter = lists[i].iterator(); iter.hasNext();)
                s += " " + iter.next();
            s += "\n";
        }

        return s;
    }

    public Graph toAltGraphRepr(Graph g) {
        // TODO: Fill in the body of this method.

        return g;
    }

    public LinkedList<AdjacencyListElement> getAdjListOfNode(int i) {
        return lists[i];
    }

}