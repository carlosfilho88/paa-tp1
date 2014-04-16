/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thrash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import org.gephi.clustering.api.Cluster;
import org.gephi.clustering.spi.Clusterer;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.EdgeIterable;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.HierarchicalUndirectedGraph;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.NodeIterable;
import org.gephi.utils.longtask.spi.LongTask;
import org.gephi.utils.progress.Progress;
import org.gephi.utils.progress.ProgressTicket;
import org.openide.util.lookup.ServiceProvider;


/**
 *
 * @author Abhi
 */
@ServiceProvider(service = Clusterer.class )
public class GirvanNewmanClusterer implements Clusterer, LongTask {    
    
    boolean cancelled = true;
    private ProgressTicket progressTicket;
    
    private boolean randomColoring = true;
    public boolean getRandomColoring() {return randomColoring;}
    public void setRandomColoring(boolean randomColoring) {this.randomColoring = randomColoring;}
    
    private boolean stepwiseUpdate = false;
    public boolean getStepwiseUpdate() {return stepwiseUpdate;}
    public void setStepwiseUpdate(boolean stepwiseUpdate) {this.stepwiseUpdate = stepwiseUpdate;}
    
    private boolean randomizedNodeOrder = false;
    public boolean getRandomizedNodeOrder() {return randomizedNodeOrder;}
    public void setRandomizedNodeOrder(boolean randomizedOrder) {this.randomizedNodeOrder = randomizedOrder;}

    int componentCount;     
    HashMap<Node, Integer> outDeg = new HashMap<Node, Integer>();
    private List<Cluster> result = new ArrayList<Cluster>();
    HashMap<Node, Integer> indices = new HashMap<Node, Integer>();
    int origEdges;    
    HashMap<Edge, Integer> edgices = new HashMap<Edge, Integer>();
    HashMap<Edge, Double> betweenness = new HashMap<Edge, Double>();                                
                                                           
    public ArrayList<ArrayList<Node>> ConnectedComponents(HierarchicalUndirectedGraph hgraph) {
        componentCount = 0;   
        int componentsSize[];        
        List<Integer> sizeList = new ArrayList<Integer>();
        ArrayList<ArrayList<Node>> componentListofList = new ArrayList<ArrayList<Node>>();                
        int N = hgraph.getNodeCount();

        //Keep track of which nodes have been seen
        int[] color = new int[N];        
        
        int seenCount = 0;
        while (seenCount < N) {
            ArrayList<Node> componentList = new ArrayList<Node>();
            //The search Q
            LinkedList<Node> Q = new LinkedList<Node>();
            //The component-list
            LinkedList<Node> component = new LinkedList<Node>();

            //Seed the search Q
            NodeIterable iter = hgraph.getNodes();
            for (Node first : iter) {
                if (color[indices.get(first)] == 0) {
                    Q.add(first);
                    iter.doBreak();
                    break;
                }
            }

            //While there are more nodes to search
            while (!Q.isEmpty()) {                
                //Get the next Node and add it to the component list
                Node u = Q.removeFirst();
                component.add(u);

                //Iterate over all of u's neighbors
                EdgeIterable edgeIter = hgraph.getEdgesAndMetaEdges(u);

                //For each neighbor
                for (Edge edge : edgeIter) {
                    Node reachable = hgraph.getOpposite(u, edge);
                    int id = indices.get(reachable);
                    //If this neighbor is unvisited
                    if (color[id] == 0) {
                        color[id] = 1;
                        //Add it to the search Q
                        Q.addLast(reachable);
                        //Mark it as used 
                    }
                }
                color[indices.get(u)] = 2;
                seenCount++;
            }
            
            for (Node s : component) {
                componentList.add(s);
            }
            componentListofList.add(componentList);
            componentCount++;            
        }                        
        return componentListofList;
    }    
    
    public void EdgeBetweenness(HierarchicalUndirectedGraph hgraph){        
        Integer nodeCount = hgraph.getNodeCount();
        Integer edgeCount = hgraph.getEdgeCount();        
                                
//        int count = 0;
        for (Node s : hgraph.getNodes()) {
            Stack<Node> S = new Stack<Node>();                    
            
            LinkedList<Node>[] P = new LinkedList[nodeCount];            
            double[] theta = new double[nodeCount];
            int[] d = new int[nodeCount];
            for (int j =0; j<nodeCount; j++){
                P[j] = new LinkedList<Node>();
                theta[j] = 0;
                d[j] = -1;
            }
            
            int s_index = indices.get(s);
            theta[s_index] = 1;
            d[s_index] = 0;
            
            LinkedList<Node> Q = new LinkedList<Node>();
            Q.addLast(s);
            while(!Q.isEmpty()) {
                Node v =Q.removeFirst();
                S.push(v);
                int v_index = indices.get(v);
                
                EdgeIterable edgeIter = hgraph.getEdgesAndMetaEdges(v);
                for(Edge edge : edgeIter){
                    Node reachable = hgraph.getOpposite(v, edge);
                    int r_index = indices.get(reachable);
                    if (d[r_index] < 0) {
                        Q.addLast(reachable);
                        d[r_index] = d[v_index] + 1;
                    }
                    if (d[r_index] == (d[v_index] + 1)) {
                        theta[r_index] = theta[r_index] + theta[v_index];
                        P[r_index].addLast(v);
                    }
                }                
            }
            
            Double[] delta = new Double[nodeCount];                        
            
            while(!S.empty()){                                    
                Node w = S.pop();                                
                Integer w_index = indices.get(w);                
                ListIterator<Node> iter1 = P[w_index].listIterator();
                while(iter1.hasNext()){
                    Node u = iter1.next();                    
                    Integer u_index = indices.get(u);
                    Double c = (theta[u_index] / theta[w_index])*(1+ delta[w_index]);
                    delta[u_index] += c;                                        
                    Edge e = hgraph.getEdge(w, u);                                        
                    betweenness.put(e, betweenness.get(e)+c);   
                }                                               
            }
        }                                
    }                       
    
    public void GirvanNewmanStep(HierarchicalUndirectedGraph hgraph){                
        int init_ncomp = ConnectedComponents(hgraph).size();
        int ncomp = init_ncomp;        
        while(ncomp <= init_ncomp){                                            
        double max = 0;
        Edge maxEdge = null;
        EdgeBetweenness(hgraph);
        for (Entry<Edge, Double> e : betweenness.entrySet()) {
            maxEdge = e.getKey();
            Double edgeBetweennessValue = e.getValue();
            if(edgeBetweennessValue > max){
                max = edgeBetweennessValue;
                }                
           }
           hgraph.removeEdge(maxEdge);
           ncomp = ConnectedComponents(hgraph).size();
        }
    }
    
    
    
    public double getModularity(HierarchicalUndirectedGraph hgraph){
        ArrayList<ArrayList<Node>> a = ConnectedComponents(hgraph);
        double Mod = 0;       
        for (int i = 0; i < a.size(); i++) {
            ArrayList<Node> linkList = a.get(i);
            double EIn=0, EEIn=0;
            for (int j=0; j< linkList.size(); j++) {
                Node node = linkList.get(j);                                
                EIn += hgraph.getTotalDegree(node);
                EEIn += outDeg.get(node);                
            }                        
            Mod += (EIn - EEIn*EEIn / (2.0*origEdges));
        }
        if(Mod == 0) {return 0;}
        else {
            return Mod/(2.0*origEdges);
        }    
    }
    
    
                
    @Override
    public void execute(GraphModel graphModel) {
        
        cancelled = false;                
        Progress.start(progressTicket);
        Progress.setDisplayName(progressTicket, "MCL Clustering");
        result = new ArrayList<Cluster>();
        
        HierarchicalUndirectedGraph hgraph = graphModel.getHierarchicalUndirectedGraphVisible();                
        hgraph.readLock();
        origEdges = hgraph.getTotalEdgeCount();
        Integer nodeIndex = 0;
        Integer edgeIndex = 0;                    
        for (Node s : hgraph.getNodes()) {
            indices.put(s, nodeIndex++);            
            outDeg.put(s, hgraph.getTotalDegree(s));
        }
        for( Edge e : hgraph.getEdges()){
            edgices.put(e, edgeIndex);
            edgeIndex++;
        }
        
        double bestQ = 0.0;        
        double Q = 0.0;
        
        while(true){
            GirvanNewmanStep(hgraph);        
            Q = getModularity(hgraph);            
            if(Q > bestQ){
                bestQ = Q;
                ArrayList<ArrayList<Node>> bestComponentList = ConnectedComponents(hgraph);
            }
            if(hgraph.getEdgeCount() == 0) {break;}            
        }
        
        hgraph.readUnlock();
                        
        Map<Integer, GirvanNewmanImpl> classCluster = new HashMap<Integer, GirvanNewmanImpl>();
        for (Map.Entry<Node, Integer> entry : indices.entrySet()){
            Integer classValue = entry.getValue();
            if(! classCluster.containsKey(classValue)){
                GirvanNewmanImpl cluster = new GirvanNewmanImpl();                                                
                cluster.setName("Cluster for class #" +classValue);                                
                classCluster.put(classValue, cluster);
            }
            classCluster.get(classValue).addNode(entry.getKey());
        }
        
        if(cancelled) return;        
        
        result.addAll(classCluster.values());
        
        long longer=189635271;
        if(randomColoring) for( Cluster c : result){            
            longer = (longer+189635273)%Long.MAX_VALUE;
            float r = ((float)(longer%255l))/255f;
            float g = (((float)(longer%255l))/255f)/255f;
            float b = ((((float)(longer%255l))/255f)/255f)/255f;
            
            for(Node n : c.getNodes()){
                n.getNodeData().setColor(r, g, b);
            }
        }                                                                            
        
        Progress.finish(progressTicket);
    }

    @Override
    public Cluster[] getClusters() {
        if (result.isEmpty()) {
            return null;
        }
        return result.toArray(new Cluster[0]);
    }

    @Override
    public boolean cancel() {
        Progress.finish(progressTicket, "Finished");
        return cancelled = true;
    }

    @Override
    public void setProgressTicket(ProgressTicket progressTicket) {
        this.progressTicket = progressTicket;
    }

}                   