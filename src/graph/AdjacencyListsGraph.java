package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class AdjacencyListElement {

	public Integer target;
    public int weight;
    
    public AdjacencyListElement(Integer target) {
        this.target = target;
        this.weight = 1;
    }

    public AdjacencyListElement(Integer target, int weight) {
        this.target = target;
        this.weight = weight;
    }

    public String toString() {
        return "" + target;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdjacencyListElement other = (AdjacencyListElement) obj;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}
}

public class AdjacencyListsGraph extends Graph {

    private ArrayList<AdjacencyListElement>[] lists;

    @SuppressWarnings("unchecked")
    public AdjacencyListsGraph(ArrayList<Integer> vertexes, List<Edge> edges, boolean directed) {
        super(vertexes, edges, directed);

        lists = new ArrayList[num_nodes];
        for (Integer vertex : vertexes) {
        	lists[vertex] = new ArrayList<AdjacencyListElement>();
		}
    }

    public void addEdge(Integer source, Integer target) {
        if (!directed) {
			lists[target].add(new AdjacencyListElement(source));
        	lists[source].add(new AdjacencyListElement(target));
        } else {
        	lists[source].add(new AdjacencyListElement(target));
        }
    }
    
    public void addEdge(Integer source, Integer target, int weight) {
        if (!directed) {
        	lists[target].add(new AdjacencyListElement(source, weight));
        	lists[source].add(new AdjacencyListElement(target, weight));
        } else {
        	lists[source].add(new AdjacencyListElement(target, weight));
        }
    }

    public int removeEdge(Integer source, Integer target) {
    	if (!directed) {
    		for (Iterator<AdjacencyListElement> iter = lists[source].iterator(); iter.hasNext();) {
        		AdjacencyListElement e = iter.next();
                if (e.target.equals(target)) {
                    iter.remove();
                }
            }
    		for (Iterator<AdjacencyListElement> iter = lists[target].iterator(); iter.hasNext();) {
        		AdjacencyListElement e = iter.next();
                if (e.target.equals(source)) {
                    iter.remove();
                    return e.weight;
                }
            }
    	} else {
        	for (Iterator<AdjacencyListElement> iter = lists[source].iterator(); iter.hasNext();) {
        		AdjacencyListElement e = iter.next();
                if (e.target.equals(target)) {
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

    public int weightOfEdge(Integer source, Integer target) {
		for (Iterator<AdjacencyListElement> iter = lists[source].iterator(); iter.hasNext();) {
    		AdjacencyListElement e = iter.next();
            if (e.target == target) {
            	return e.weight;
            }
        }
        return Integer.MAX_VALUE;
    }

    public String toString() {
    	String s = "";
        for (int i = 0; i < num_nodes; i++) {
            s += Main.ivMap.get(i) + ": [";

            for (Iterator<AdjacencyListElement> iter = lists[i].iterator(); iter.hasNext();)
                s += " " + Main.ivMap.get(iter.next().target);
            s += " ]\n";
        }

        return s;
    }

    public Graph toAltGraphRepr(Graph g) {
        return g;
    }

    public ArrayList<AdjacencyListElement> getAdjListOfNode(Integer vertex) {
        return lists[vertex];
    }
    
    public ArrayList<AdjacencyListElement>[] getAdjLists() {
        return lists;
    }

}