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
        return "(" + target + "; " + weight + ")";
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

    public void addEdge(int source, int target, int weight) {
    	if (source >= 0 && target >= 0 && source < num_nodes && target < num_nodes) {
            if (!directed) {
            	lists[target].addLast(new AdjacencyListElement(source, weight));
            	lists[source].addLast(new AdjacencyListElement(target, weight));
            } else {
            	lists[source].addLast(new AdjacencyListElement(target, weight));
            }
        } else
            throw new IndexOutOfBoundsException();
    }

    public int removeEdge(int source, int target) {
    	if (!directed) {
    		for (Iterator<AdjacencyListElement> iter = lists[source].iterator(); iter.hasNext();) {
        		AdjacencyListElement e = iter.next();
                if (e.target == target) {
                    iter.remove();
                }
            }
    		for (Iterator<AdjacencyListElement> iter = lists[target].iterator(); iter.hasNext();) {
        		AdjacencyListElement e = iter.next();
                if (e.target == source) {
                    iter.remove();
                    return e.weight;
                }
            }
    	} else {
        	for (Iterator<AdjacencyListElement> iter = lists[source].iterator(); iter.hasNext();) {
        		AdjacencyListElement e = iter.next();
                if (e.target == target) {
                    iter.remove();
                    return e.weight;
                }
            }
        }
        return Integer.MAX_VALUE;
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
        return g;
    }

    public LinkedList<AdjacencyListElement> getAdjListOfNode(int i) {
        return lists[i];
    }

}