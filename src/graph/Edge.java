package graph;

public class Edge implements EdgeInterface {

    private int source, target, weight;

    public Edge(int source, int target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public int source() {
        return source;
    }

    public int target() {
        return target;
    }

    public int weight() {
        return weight;
    }

    public String toString() {
        return "(" + source + "," + target + ")\t" + weight;
    }

}