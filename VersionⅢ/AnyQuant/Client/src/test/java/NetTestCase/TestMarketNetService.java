package NetTestCase;

import data.MACDDataImp;
import junit.framework.TestCase;
import net.MarketNetImpl;
import netservice.MarketNetService;
import po.BenchMarkInfoPO;
import po.NowTimeBenchMarkInfoPO;
import po.macdPO;
import vo.IncreasementVO;

import java.util.ArrayList;

/**
 * Created by HP on 2016/3/3.
 */
public class TestMarketNetService extends TestCase{


    MarketNetService marketTest=new MarketNetImpl();
    public void  testGetAllBenchMark(){
//        ArrayList<String> benchmarkList=marketTest.getAllBenchMark();
//        for(String name:benchmarkList){
//            System.out.println(name);
//        }

    }

    public void testGetBenchMarkInfoByTimeOrByMarket(){
//        ArrayList<BenchMarkInfoPO> list=marketTest.getBenchMarkInfoByTimeOrByMarket("2015-01-01","2015-02-01","hs300");
//        for(BenchMarkInfoPO po:list){
//            System.out.println(po.getAdj_price()+" "+po.getDate());
//        }

    }

    public void testGetNowTimeBenchMarkInfo(){
//       NowTimeBenchMarkInfoPO po=marketTest.getNowTimeBenchMarkInfo();
//        System.out.println(po.getDealNum()+" "+po.getDealPri()+" "+po.getHighPri()+" "+po.getIncrease()+" "+po.getIncrePer()+" "+po.getLowpri()
//                +" "+po.getNowpri()+" "+po.getOpenPri()+" "+po.getYesPri()+" "+po.getTime()+" "+po.getName());
    }

    public void testGetData(){
//        MACDDataImp macdDataImp=new MACDDataImp();
////        ArrayList<macdPO> list= (ArrayList<macdPO>) macdDataImp.getData("2016","2016-01-07","sh600000","DEA");
////        for(double d:list){
////            System.out.println(d);
////        }
//        macdDataImp.update();

    }


}
