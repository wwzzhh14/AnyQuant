package businesslogic.visualizationlogic;

import businesslogicservice.visualizationlogicservice.VisualizationStockData;


import net.StockNetImpl;
import netservice.StockNetService;

import org.jfree.data.time.Day;

import po.StockInfoPO;

import vo.KlineVO;
import vo.LineAndHistogramChartVO;

import java.util.ArrayList;

import java.util.List;

/**
 * Created by Jiayiwu on 16/3/19.
 */
public class VisualizationStockDataimpl implements VisualizationStockData {

    private StockNetService stockNet=new StockNetImpl();

    public List<KlineVO> getKlineDate(String start, String end, String codeNum) {

        ArrayList<StockInfoPO> poList=stockNet.getStockInfoByTime(codeNum,start,end);
        List<KlineVO> voList=new ArrayList<KlineVO>();
        if(poList!=null){
            for(StockInfoPO po:poList){
                String[] date=po.getDate().split("-");
                voList.add(new KlineVO(new Day(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0])),po.getOpen(),
                        po.getHigh(),po.getLow(),po.getClose(),po.getVolume()));
            }
            return voList;
        }else {
            return null;
        }
    }


    public List<LineAndHistogramChartVO> getLineAndHistogramStockDate(String start, String end, String codeNum) {
        List<LineAndHistogramChartVO> voList=new ArrayList<LineAndHistogramChartVO>();
        ArrayList<StockInfoPO> poList=stockNet.getStockInfoByTime(codeNum,start,end);
        if(poList!=null){

            for(StockInfoPO po:poList){
                String[] date=po.getDate().split("-");
                voList.add(new LineAndHistogramChartVO(po.getClose(),new Day(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0])),po.getVolume()));
            }
            return voList;
        }else {
            return null;
        }
    }
}
