package businesslogic.visualizationlogic;

import businesslogicservice.visualizationlogicservice.VisualizationMarketData;
import net.MarketNetImpl;
import netservice.MarketNetService;
import org.jfree.data.time.Day;
import po.BenchMarkInfoPO;
import po.StockInfoPO;
import vo.KlineVO;
import vo.LineAndHistogramChartVO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiayiwu on 16/3/21.
 */
public class VisualizationMarketDataimpl implements VisualizationMarketData {

    MarketNetService marketNet=new MarketNetImpl();
    public List<KlineVO> getKlineDate(String start, String end, String marketNum) {

        ArrayList<BenchMarkInfoPO> poList= marketNet.getBenchMarkInfoByTimeOrByMarket(start,end,marketNum);
        List<KlineVO> voList=new ArrayList<KlineVO>();
        if(poList!=null){
            for(BenchMarkInfoPO po:poList){
                String[] date=po.getDate().split("-");
                voList.add(new KlineVO(new Day(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0])),po.getOpen(),
                        po.getHigh(),po.getLow(),po.getClose(),po.getVolume()));
            }
            return voList;
        }else {
            return null;
        }
    }

    public List<LineAndHistogramChartVO> getLineAndHistogramStockDate(String start, String end, String marketNum) {
        List<LineAndHistogramChartVO> voList=new ArrayList<LineAndHistogramChartVO>();
        ArrayList<BenchMarkInfoPO> poList= marketNet.getBenchMarkInfoByTimeOrByMarket(start,end,marketNum);
        if(poList!=null){
            for(BenchMarkInfoPO po:poList){
                String[] date=po.getDate().split("-");
                voList.add(new LineAndHistogramChartVO(po.getClose(),new Day(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0])),po.getVolume()));
            }
            return voList;
        }else {
            return null;
        }
    }
}
