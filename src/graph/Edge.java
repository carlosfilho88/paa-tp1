package graph;

public class Edge implements EdgeInterface {

    private Integer source, target, weight, betweenness;
    
    public Edge(Integer source, Integer target) {
        this.source = source;
        this.target = target;
        this.weight = 1;
        this.betweenness = 0;
    }

    public Edge(Integer source, Integer target, Integer weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        this.betweenness = 0;
    }
    
    public Edge(Integer source, Integer target, Integer weight, Integer betweenness) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        this.betweenness = betweenness;
    }

    public Integer source() {
        return source;
    }

    public Integer target() {
        return target;
    }

    public Integer weight() {
        return weight;
    }
    
    public Integer betweenness() {
        return betweenness;
    }
    
    public String toString() {
        return "(" + source + "," + target + ")\t" + weight + "["+ betweenness + "]";
    }

}