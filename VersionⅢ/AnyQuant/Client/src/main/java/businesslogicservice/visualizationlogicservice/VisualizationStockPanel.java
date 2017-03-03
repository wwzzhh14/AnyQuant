package businesslogicservice.visualizationlogicservice;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiayiwu on 16/3/18.
 */
public interface VisualizationStockPanel {
    public JPanel getKlineStockPanel (String start,String end,String codeNum);

    public JPanel getLineAndHistogramStockPanel(String start,String end,String codeNum);

    //获取Macd图和增长曲线对比图
    public JPanel getMacdAndIncrementStockPanel(String start,String end,String codeNum,String markNum);

    public JPanel getCompareStockPanel(String codeNum, boolean recommend, Color color);

    public JPanel getARIMAStockPanel(String codeNum,int dayNum,Color start,Color end);
}
