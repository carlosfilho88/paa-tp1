package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Betweeness {

	private AdjacencyMatrixGraph graph;
	public static int[][] dists;
	public static int[][] nPaths;
	public static int[][] sigma;
	public static int[][] betweenness;
	

	public Betweeness(AdjacencyMatrixGraph graph) {
		this.graph = graph;
		dists = new int[graph.getNumNodes()][graph.getNumNodes()];
		nPaths = new int[graph.getNumNodes()][graph.getNumNodes()];
		sigma = new int[graph.getNumNodes()][graph.getNumNodes()];
		betweenness = new int[graph.getNumNodes()][graph.getNumNodes()];
		for (int i = 0; i < nPaths.length; i++) {
			for (int j = 0; j < nPaths.length; j++) {
				nPaths[i][j] = -1;
				sigma[i][j] = 1;
				dists[i][j] = 0;
				betweenness[i][j] = 0;
			}
		}
	}

	public String BFS(Integer source) {
		boolean[] marked = new boolean[graph.getNumNodes()];
		Integer node;
//		String s = "BFS for " + Main.ivMap.get(source) + ": ";
		String s = "";
		Queue<Integer> queue = new LinkedList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();

		marked[source] = true;
		queue.add(source);
		stack.add(source);

		while (!queue.isEmpty()) {
			node = queue.poll();
			s += Main.ivMap.get(node) + "\t";
			for (int i = 0; i < graph.getNumNodes(); i++) {
				if ((graph.edges[node][i] == 1) && !(marked[i])) {
					marked[i] = true;
					queue.add(i);
					stack.add(i);
					dists[source][i] = dists[source][node] + 1;
					nPaths[source][i] = node;
				}
			}
		}
		Integer current, predecessor;
		while(!stack.empty()) {
			current = stack.pop();
			if(source != current) {
				predecessor = nPaths[source][current];
				betweenness[predecessor][current] += sigma[source][current];
//				betweenness[current][predecessor] += sigma[source][current];
				//betweenness[current][predecessor] += sigma[source][current];
				//betweenness[predecessor][current] = betweenness[current][predecessor];
				sigma[source][predecessor] += sigma[source][current];
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
			if(Main.ivMap.containsKey(nPaths[i][j]))
				s += Main.ivMap.get(nPaths[i][j]) + " ";
			else 
				s += "- ";
			}
			s += "\n";
		}
		return s;
	}
	
	public String toStringBetweenness() {
		String s = "";
		for (int i = 0; i <= graph.getNumNodes(); i++) {
			if(Main.ivMap.containsKey(i)) {
				s += Main.ivMap.get(i) + "\t";
				}
		}
		s += "\n";
		for (int i = 0; i <= graph.getNumNodes(); i++) {
			if(Main.vMap.containsKey(i)) {
				int a = Main.vMap.get(i);
				for (int j = 0; j <= graph.getNumNodes(); j++) {
					if(Main.vMap.containsKey(j)) {
						int b = Main.vMap.get(j);
						s += betweenness[a][b] + "\t";
					} 
				}
				s += "\n";
			}
		}
		return s;
	}
}