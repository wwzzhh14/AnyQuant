package NetTestCase;

import junit.framework.TestCase;
import net.MarketNetImpl;
import netservice.MarketNetService;
import po.BenchMarkInfoPO;

import java.util.ArrayList;

/**
 * Created by HP on 2016/3/3.
 */
public class TestMarketNetService extends TestCase {


    MarketNetService marketTest=new MarketNetImpl();
    public void  testGetAllBenchMark(){
        ArrayList<String> benchmarkList=marketTest.getAllBenchMark();
        for(String name:benchmarkList){
            System.out.println(name);
        }

    }

    public void testGetBenchMarkInfoByTimeOrByMarket(){
        ArrayList<BenchMarkInfoPO> list=marketTest.getBenchMarkInfoByTimeOrByMarket("2015-01-01","2015-02-01","hs300");
        for(BenchMarkInfoPO po:list){
            System.out.println(po.getAdj_price()+" "+po.getDate());
        }

    }
}
