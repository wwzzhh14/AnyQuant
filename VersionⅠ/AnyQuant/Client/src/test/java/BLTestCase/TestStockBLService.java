package BLTestCase;

import businesslogic.StockInfoBLImpl;
import businesslogicservice.StockInfoBLService;
import config.ExchangePlace;
import config.StockName;
import junit.framework.TestCase;
import vo.StockInfoVO;

import java.util.ArrayList;

/**
 * Created by HP on 2016/3/7.
 */
public class TestStockBLService extends TestCase{

    StockInfoBLService stockInfoBLService=new StockInfoBLImpl();
//    String year, ExchangePlace place
    public void testGetStockByYearOrByPlace(){
        ArrayList<StockInfoVO> list=stockInfoBLService.getStockByYearOrByPlace("2016",ExchangePlace.sh);
        for(StockInfoVO vo:list){
            System.out.println("Adj_price:"+vo.getAdj_price()+" "+"Adj_Date:"+vo.getDate()+" "+"Open:"+vo.getOpen()+" "+"Low:"+vo.getLow()+" "+"Volume:"+vo.getVolume()+" "+"High:"+vo.getHigh()
                    +" "+"Code_Num:"+vo.getCodeNum()+" "+"Name::"+vo.getName());
        }
        System.out.println("*********************************************");
    }
    //    返回某只股票的详细信息，默认为2016-01-01到2016-02-01
    public void testGetStockInfo(){
        ArrayList<StockInfoVO> list=stockInfoBLService.getStockInfo((String) StockName.getStockNameAndCode().keySet().toArray()[5]);
        for(StockInfoVO vo:list){
            System.out.println(vo.getAdj_price()+" "+vo.getDate()+" "+vo.getOpen()+" "+vo.getLow()+" "+vo.getVolume()+" "+vo.getHigh()
                    +" "+vo.getCodeNum()+" "+vo.getName());
        }
        System.out.println("*********************************************");
    }
//     返回某只股票某段时间内的详细信息


    public void testGetStockInfoByTime(){
        ArrayList<StockInfoVO> list=stockInfoBLService.getStockInfoByTime((String) StockName.getStockNameAndCode().keySet().toArray()[2],"2015-11-01","2015-12-09");
        for(StockInfoVO vo:list){
            System.out.println(vo.getAdj_price()+" "+vo.getDate()+" "+vo.getOpen()+" "+vo.getLow()+" "+vo.getVolume()+" "+vo.getHigh()
                    +" "+vo.getCodeNum()+" "+vo.getName());
        }
    }
}
