/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thrash;

import java.util.ArrayList;
import java.util.List;
import org.gephi.clustering.api.Cluster;
import org.gephi.graph.api.Node;
import java.util.Arrays;

/**
 *
 * @author Abhi
 */
public class GirvanNewmanImpl implements Cluster {
    private List<Node> nodes = new ArrayList<Node>();
    private String clusterName = "untitled";
    private Node metanode = null;

    public GirvanNewmanImpl() {
    }
    
    public GirvanNewmanImpl(Node[] nodes) {
        this.nodes = Arrays.asList(nodes);
    }
    
    public void addNode( Node node ){
        this.nodes.add(node);
    }
    
    public void setName(String clusterName){
        this.clusterName = clusterName;
    }

    @Override
    public Node[] getNodes() {
        return this.nodes.toArray(new Node[0]);
    }

    @Override
    public int getNodesCount() {
        return this.nodes.size();
    }

    @Override
    public String getName() {
        return this.clusterName;
    }

    @Override
    public Node getMetaNode() {
        return this.metanode;
    }   

    @Override
    public void setMetaNode(Node node) {
        this.metanode = node;
    }
    
    
    
    
    
    
    
    
    
    

}
