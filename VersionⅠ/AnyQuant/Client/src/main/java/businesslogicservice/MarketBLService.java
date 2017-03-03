package businesslogicservice;

import vo.BenchMarkInfoVO;
import vo.SelectValueBean;


import java.util.ArrayList;

/**
 * Created by HP on 2016/3/2.
 */
public interface MarketBLService {

//    用于返回所有可用的大盘，目前只有hs300
    public ArrayList<String> getAllBenchMark();
//   用于返回某段时间内某大盘的详细信息。当前两个参数为null时，默认startTime=2016-01-01,endTime=2016-02-01
    public ArrayList<BenchMarkInfoVO> getBenchMarkInfoByTimeOrByMarket(String startTime, String endTime, String benchmark);

    public ArrayList<BenchMarkInfoVO> getBenchMarkInfoByTimeOrBySelect(String startTime, String endTime, String benchmark
    ,SelectValueBean open, SelectValueBean high ,SelectValueBean close,SelectValueBean low ,SelectValueBean volume, SelectValueBean adj_price
                                                                       );
}