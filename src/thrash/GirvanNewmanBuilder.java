/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thrash;

import org.gephi.clustering.spi.Clusterer;
import org.gephi.clustering.spi.ClustererBuilder;
import org.gephi.clustering.spi.ClustererUI;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Abhi
 */
@ServiceProvider(service = ClustererBuilder.class )
public class GirvanNewmanBuilder<T> implements ClustererBuilder {

    @Override
    public Clusterer getClusterer() {
        return new GirvanNewmanClusterer();
    }

    @Override
    public String getName() {
       return "Girvan Newman";
    }                

    @Override
    public String getDescription() {
        return "Girvan Newman Clustering";
    }

    @Override
    public Class getClustererClass() {
        return GirvanNewmanClusterer.class;
    }

    @Override
    public ClustererUI getUI() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
