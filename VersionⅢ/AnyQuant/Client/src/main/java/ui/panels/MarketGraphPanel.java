package ui.panels;

import businesslogic.visualizationlogic.VisualizationStockPanelimpl;
import businesslogicservice.visualizationlogicservice.VisualizationStockPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dell on 2016/5/8.
 */
public class MarketGraphPanel extends JPanel{
    JPanel compareStockPanel,compareStockPanel2;
    VisualizationStockPanel controller=new VisualizationStockPanelimpl();
    public MarketGraphPanel(String name){
        compareStockPanel=controller.getCompareStockPanel(name,false, Color.cyan);
        compareStockPanel.setBounds(50,10,400,300);
        this.add(compareStockPanel);
        compareStockPanel2=controller.getCompareStockPanel(name,true,Color.red);
        compareStockPanel2.setBounds(470,10,400,300);
this.add(compareStockPanel2);
    }
}
