package graph;

import java.util.LinkedList;
import java.util.Queue;

public class Betweeness {

	private Graph graph;
	public static int[][] dists;
	public static int[][] nPaths;

	public Betweeness(Graph graph) {
		this.graph = (AdjacencyMatrixGraph) graph;
		dists = new int[graph.getNumNodes()][graph.getNumNodes()];
		nPaths = new int[graph.getNumNodes()][graph.getNumNodes()];
	}

	public String BFS(Integer source) {
		boolean[] marked = new boolean[graph.getNumNodes()];
		Integer node;
		String s = "BFS for " + Main.ivMap.get(source) + ": ";
		Queue<Integer> queue = new LinkedList<Integer>();

		marked[source] = true;
		queue.add(source);

		while (!queue.isEmpty()) {
			node = queue.poll();
			s += Main.ivMap.get(node) + " ";
			for (int i = 0; i < graph.getNumNodes(); i++) {
				if ((((AdjacencyMatrixGraph) graph).edges[node][i] == 1) && !(marked[i])) {
					marked[i] = true;
					queue.add(i);
				}
			}
		}
		return s;
	}

	public String toStringDists() {
		String s = "";
		for (int i = 0; i < graph.getNumNodes(); i++) {
			for (int j = 0; j < graph.getNumNodes(); j++) {
				s += dists[i][j] + " ";
			}
			s += "\n";
		}
		return s;
	}
	
	public String toStringNPaths() {
		String s = "";
		for (int i = 0; i < graph.getNumNodes(); i++) {
			for (int j = 0; j < graph.getNumNodes(); j++) {
				s += nPaths[i][j] + " ";
			}
			s += "\n";
		}
		return s;
	}
	// OLD CODE BASED ON ADJACENCY MATRIX
	
    /**
     * Computes matrix of distances and #s of shortest paths between any 2 vertices
     * @param n size of matrix/graph
     * @param adj adjacency matrix of graph
     * @param dists n x n matrix to set up with distances (return value)
     * @param nPaths n x n matrix to set up with shortest paths (return value)
     */

    public void computeShortestPaths() {
    	int n = graph.getNumNodes();
        int[][] curAdj = new int[n][n];
        int power = 1;
        for (int i = 0; i < n; i++) {
            dists[i][i] = 0;
            nPaths[i][i] = 1;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                curAdj[i][j] = ((AdjacencyMatrixGraph) graph).edges[i][j];
                if (((AdjacencyMatrixGraph) graph).edges[i][j] == 0) {
                    dists[i][j] = nPaths[i][j] = -1;
                } else {
                    dists[i][j] = nPaths[i][j] = 1;
                }
            }
        }
        int nFound = -1;
        while (nFound != 0) {
            nFound = 0;
            curAdj = matrixProduct(curAdj, ((AdjacencyMatrixGraph) graph).edges);
            power++;
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    if (i == j) continue;
                    if (dists[i][j] == -1 && curAdj[i][j] != 0) {
                        dists[i][j] = power;
                        nPaths[i][j] = curAdj[i][j];
                        nFound++;
                    }
                }
        }
    } // computeShortestPaths

    /**
     * Computes the betweenness of a specified vertex
     * @param n size of matrix/graph
     * @param dists the matrix of distances between vertices
     * @param nPaths the matrix of # of shortest paths between any two vertices
     * @param source the index of the node whose betweenness is to be computed
     * @param directed whether the graph for the computation is directed or not
     * @return the betweenness of the vertex
     */
    public int computeBetweenness(Integer source) {
    	int n = graph.getNumNodes();
    	int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                else if ((i == source || j == source) && dists[i][j] != -1)
                    result++;
                else if (dists[i][source] != -1 && dists[source][j] != -1 && dists[i][j] != -1 && dists[i][source] + dists[source][j] == dists[i][j])
                    result += nPaths[i][source] * nPaths[source][j] / nPaths[i][j];
            }
        }
        return result;
    }
    
    public static int[][] matrixProduct(int[][] m1, int[][] m2) {
        int rows1 = m1.length, cols1 = m1[0].length;
        int rows2 = m2.length, cols2 = m2[0].length;
        if (cols1 != rows2)
            throw new IllegalArgumentException("matrixProduct: incompatible matrix sizes");
        int[][] result = new int[rows1][cols2];
        for (int i = 0; i < rows1; i++)
            for (int j = 0; j < rows2; j++) {
                int sum = 0;
                for (int k = 0; k < rows1; k++)
                    sum += m1[i][k]*m2[k][j];
                result[i][j] = sum;
            }
        return result;
    }

	/**
     * Performs breadth-first search algorithm to enumerate the nodes in a graph,
     * starting from the specified start node.
     * @param graph the graph under consideration
     * @param start the starting node.
     * @param numShortest a map that will be filled with info on the # of shortest paths
     * @param lengths a map that will be filled with info on the lengths of shortest paths
     * @param stack a stack that will be filled with elements in non-increasing order of distance 
     * @param pred a map that will be filled with adjacency information for the shortest paths
     */
	/*
    public static <V> void breadthFirstSearch(Graph<V> graph, V start,
            Map<V, Integer> numShortest, Map<V, Integer> lengths,
            Stack<V> stack, Map<V,Set<V>> pred) {

        List<V> nodes = graph.nodes();
        for (V v : nodes) numShortest.put(v, 0);
        numShortest.put(start, 1);
        for (V v : nodes) lengths.put(v, -1);
        lengths.put(start, 0);
        for (V v : nodes) pred.put(v, new HashSet<V>());

        // breadth-first search algorithm
        LinkedList<V> queue = new LinkedList<V>(); // tracks elements for search algorithm

        queue.add(start);
        while (!queue.isEmpty()) {
            V v = queue.remove();
            stack.addElement(v);
            for (V w : graph.neighbors(v)) {
                // if w is found for the first time in the tree, add it to the queue, and adjust the length
                if (lengths.get(w) == -1) {
                    queue.add(w);
                    lengths.put(w, lengths.get(v) + 1);
                }
                // adjust the number of shortest paths to w if shortest path goes through v
                if (lengths.get(w) == lengths.get(v) + 1) {
                    numShortest.put(w, numShortest.get(w) + numShortest.get(v));
                    pred.get(w).add(v);
                }
            }
        }
//        System.out.println("      lengths: " + lengths);
//        System.out.println("      #paths:  " + numShortest);
//        System.out.println("  stack: " + stack);
//        System.out.println("  preds: " + pred);

    } // METHOD breadthFirstSearch
    */
}

