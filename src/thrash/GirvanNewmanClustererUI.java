/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thrash;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import org.gephi.clustering.spi.Clusterer;
import org.gephi.clustering.spi.ClustererUI;

/**
 *
 * @author Abhi
 */
public class GirvanNewmanClustererUI implements ClustererUI {

    JPanel panel = null;
    JCheckBox randomColoring = null;
    JCheckBox stepwiseUpdate = null;
    JCheckBox randomizedNodeOrder = null;
    
    GirvanNewmanClusterer clusterer = null;

    public GirvanNewmanClustererUI() {
        initComponents();
    }
    
    
    
    @Override
    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void setup(Clusterer clusterer) {
        this.clusterer = (GirvanNewmanClusterer) clusterer;
        randomColoring.setSelected(this.clusterer.getRandomColoring());
        stepwiseUpdate.setSelected(this.clusterer.getStepwiseUpdate());
        randomizedNodeOrder.setSelected(this.clusterer.getRandomizedNodeOrder());
    }

    @Override
    public void unsetup() {                
        clusterer.setRandomColoring(randomColoring.isSelected());                
        clusterer.setStepwiseUpdate(stepwiseUpdate.isSelected());        
        clusterer.setRandomizedNodeOrder(randomizedNodeOrder.isSelected());

    }
    
    private JPanel configLine( String labelText, JComponent component){
        JPanel configLine = new JPanel();
        configLine.setAlignmentX(Component.LEFT_ALIGNMENT);
        configLine.setLayout(new BoxLayout(configLine, BoxLayout.X_AXIS));
        JLabel label = new JLabel(labelText+":", JLabel.RIGHT);
        configLine.add(label);
        configLine.add(Box.createRigidArea(new Dimension(8,1)));
        configLine.add(component);
        configLine.add(Box.createHorizontalGlue());
        return configLine;
    }

    private void initComponents() {
        panel = new JPanel(true);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(configLine("Perform Random Coloring", randomColoring = new JCheckBox()));
        panel.add(new JSeparator());
        panel.add(configLine("Stepwise Updates", stepwiseUpdate = new JCheckBox()));
        panel.add(configLine("Randomized Node Order", randomizedNodeOrder = new JCheckBox()));                
    }
    
    
    
}
