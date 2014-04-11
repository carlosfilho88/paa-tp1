package graph;

public class Edge implements EdgeInterface {

    private Integer source, target, weight;
    
    public Edge(Integer source, Integer target) {
        this.source = source;
        this.target = target;
        this.weight = 1;
    }

    public Edge(Integer source, Integer target, Integer weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public Integer source() {
        return source;
    }

    public Integer target() {
        return target;
    }

    public int weight() {
        return weight;
    }

    public String toString() {
        return "(" + source + "," + target + ")\t" + weight;
    }

}