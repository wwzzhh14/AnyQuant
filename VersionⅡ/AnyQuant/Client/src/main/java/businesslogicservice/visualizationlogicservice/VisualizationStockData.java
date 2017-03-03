package businesslogicservice.visualizationlogicservice;

import vo.KlineVO;
import vo.LineAndHistogramChartVO;

import javax.swing.*;
import java.util.List;

/**
 * Created by Jiayiwu on 16/3/19.
 */
public interface VisualizationStockData {
    public List<KlineVO> getKlineDate(String start,String end,String codeNum);
    public List<LineAndHistogramChartVO> getLineAndHistogramStockDate(String start, String end, String codeNum);
}
