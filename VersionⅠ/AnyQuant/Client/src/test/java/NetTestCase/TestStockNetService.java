package NetTestCase;

import junit.framework.TestCase;
import net.StockNetImpl;
import netservice.StockNetService;
import po.StockInfoPO;

import java.util.ArrayList;

/**
 * Created by HP on 2016/3/3.
 */
public class TestStockNetService extends TestCase {

    StockNetService testStockNet=new StockNetImpl();

    public void testGetStockByYearOrByPlace(){
//        ArrayList<String> result=testStockNet.getStockByYearOrByPlace("2009", ExchangePlace.sh);
//        for(String s:result){
//            System.out.println(s);
//        }

    }

    public void testGetStockInfo(){
        ArrayList<StockInfoPO> list=testStockNet.getStockInfo("sh600216");
        for(StockInfoPO po:list){
            System.out.println(po.getAdj_price()+" "+po.getClose()+" "+po.getHigh()+" "+po.getLow()+" "+po.getOpen()+" "+
            po.getPb()+" "+po.getPe_ttm()+" "+po.getTurnover()+" "+po.getVolume()+" "+po.getDate());
        }

    }

    public void testGetStockInfoByTime(){

    }
}
