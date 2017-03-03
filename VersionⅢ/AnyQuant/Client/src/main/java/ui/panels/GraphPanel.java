package ui.panels;

import businesslogic.visualizationlogic.VisualizationStockPanelimpl;
import businesslogicservice.visualizationlogicservice.VisualizationStockPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dell on 2016/5/8.
 */
public class GraphPanel extends JPanel{
    JPanel compareStockPanel,compareStockPanel2,arimaStockPanel;
    VisualizationStockPanel controller=new VisualizationStockPanelimpl();
    public GraphPanel(String name){
        this.setLayout(null);
        compareStockPanel=controller.getCompareStockPanel(name,true, Color.cyan);
        compareStockPanel.setBounds(0,0,600,350);
        this.add(compareStockPanel);
        compareStockPanel2=controller.getCompareStockPanel(name,false,Color.red);
        compareStockPanel2.setBounds(600,0,400,350);
        this.add(compareStockPanel2);
        arimaStockPanel=controller.getARIMAStockPanel(name,120,Color.GREEN,Color.red);
        arimaStockPanel.setBounds(0,350,1000,340);
        this.add(arimaStockPanel);
    }
}
