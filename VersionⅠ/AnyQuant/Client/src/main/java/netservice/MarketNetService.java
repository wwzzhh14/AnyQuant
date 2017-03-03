package netservice;

import net.MarketNetImpl;
import po.BenchMarkInfoPO;

import java.util.ArrayList;

/**
 * Created by HP on 2016/3/2.
 */
public interface MarketNetService {

//
    public ArrayList<String> getAllBenchMark();

    public ArrayList<BenchMarkInfoPO> getBenchMarkInfoByTimeOrByMarket(String startTime, String endTime, String benchmark);
}
