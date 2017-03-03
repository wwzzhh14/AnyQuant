package BLTestCase;

import businesslogic.MarketBlLImpl;
import businesslogicservice.MarketBLService;
import junit.framework.TestCase;
import vo.BenchMarkInfoVO;

import java.util.ArrayList;

/**
 * Created by HP on 2016/3/7.
 */
public class TestMarketBLService extends TestCase{
    //    用于返回所有可用的大盘，目前只有hs300

    MarketBLService marketBLService=new MarketBlLImpl();
    public void testGetAllBenchMark(){
        ArrayList<String> list=marketBLService.getAllBenchMark();
        for(String s:list){
            System.out.println(s);
        }
    }
    //   用于返回某段时间内某大盘的详细信息。当前两个参数为null时，默认startTime=2016-01-01,endTime=2016-02-01
//    String startTime, String endTime, String benchmark
    public void testGetBenchMarkInfoByTimeOrByMarket(){
        ArrayList<BenchMarkInfoVO> list=marketBLService.getBenchMarkInfoByTimeOrByMarket("2016-01-01","2016-02-01","hs300");
        for(BenchMarkInfoVO vo:list){
            System.out.println(vo.getAdj_price()+" "+vo.getClose()+" "+vo.getHigh()+" "+vo.getLow()+" "+vo.getOpen()+" "+vo.getDate());
        }
    }
}
