package businesslogicservice.visualizationlogicservice;

import javax.swing.*;

/**
 * Created by Jiayiwu on 16/3/20.
 */
public interface VisualizationMarketPanel {
    public JPanel getKlineStockPanel (String start, String end, String markketNum);

    public JPanel getLineAndHistogramStockPanel(String start,String end,String markketNum);
}
