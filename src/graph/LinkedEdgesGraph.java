package graph;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedEdgesGraph extends Graph {

    private LinkedList<EdgeInterface> edges;

    public LinkedEdgesGraph(int nodes, boolean directed) {
        super(nodes, directed);
        edges = new LinkedList<EdgeInterface>();
    }

    public void addEdge(int source, int target, int weight) {
        if (source >= 0 && target >= 0 && source < num_nodes && target < num_nodes) {
            if (directed)
                edges.addFirst(new Edge(source, target, weight));
            else {
                int from = Math.min(source, target);
                int to = Math.max(source, target);
                edges.addFirst(new Edge(from, to, weight));
            }
        } else
            throw new IndexOutOfBoundsException();
    }

    public int removeEdge(int source, int target) {
        if (!directed) {
            int from = Math.min(source, target);
            target = Math.max(source, target);
            source = from;
        }

        for (Iterator<EdgeInterface> iter = edges.iterator(); iter.hasNext();) {
            EdgeInterface e = iter.next();
            if (e.source() == source && e.target() == target) {
                iter.remove();
                return e.weight();
            }
        }

        return Integer.MAX_VALUE;
    }

    public void removeAllEdges() {
        edges.clear();
    }

    public int weightOfEdge(int source, int target) {
        if (!directed) {
            int from = Math.min(source, target);
            target = Math.max(source, target);
            source = from;
        }

        for (Iterator<EdgeInterface> iter = edges.iterator(); iter.hasNext();) {
            EdgeInterface e = iter.next();
            if (e.source() == source && e.target() == target)
                return e.weight();
        }

        return Integer.MAX_VALUE;
    }

    public String toString() {
        String s = "";
        for (Iterator<EdgeInterface> iter = edges.iterator(); iter.hasNext();)
            s += iter.next() + "\n";

        return s;
    }

    public Graph toAltGraphRepr(Graph g) {
        if (g.isDirected() == directed && g.getNumNodes() == num_nodes) {
            g.removeAllEdges();

            for (Iterator<EdgeInterface> iter = edges.iterator(); iter.hasNext();)
                g.addEdge(iter.next());
        }
        return g;
    }

    public LinkedList<EdgeInterface> getEdges() {
        return edges;
    }
}