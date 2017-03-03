package businesslogicservice.visualizationlogicservice;

import vo.KlineVO;
import vo.LineAndHistogramChartVO;

import java.util.List;

/**
 * Created by Jiayiwu on 16/3/20.
 */
public interface VisualizationMarketData {
    public List<KlineVO> getKlineDate(String start, String end, String marketNum);
    public List<LineAndHistogramChartVO> getLineAndHistogramStockDate(String start, String end, String marketNum);
}
