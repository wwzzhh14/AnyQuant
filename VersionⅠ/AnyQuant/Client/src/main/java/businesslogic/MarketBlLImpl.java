package businesslogic;

import businesslogicservice.MarketBLService;
import net.MarketNetImpl;
import netservice.MarketNetService;
import po.BenchMarkInfoPO;
import vo.BenchMarkInfoVO;
import vo.SelectValueBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by HP on 2016/3/5.
 * Modified by Jiayiwu on 2016/3/11
 */
public class MarketBlLImpl implements MarketBLService{

    private MarketNetService marketNet=new MarketNetImpl();

    public ArrayList<String> getAllBenchMark() {
        return marketNet.getAllBenchMark();
    }

    public ArrayList<BenchMarkInfoVO> getBenchMarkInfoByTimeOrByMarket(String startTime, String endTime, String benchmark) {
        ArrayList<BenchMarkInfoPO> poList=marketNet.getBenchMarkInfoByTimeOrByMarket(startTime,endTime,benchmark);
        ArrayList<BenchMarkInfoVO> voList=new ArrayList<BenchMarkInfoVO>();
        if(poList!=null){
            for(BenchMarkInfoPO po:poList){
                if(po!=null){
                    voList.add(new BenchMarkInfoVO(po.getDate(),po.getOpen(),po.getHigh(),po.getClose(),po.getLow(),po.getVolume(),po.getAdj_price()));
                }
            }
            return voList;
        }
        return null;
    }


    public ArrayList<BenchMarkInfoVO> getBenchMarkInfoByTimeOrBySelect(String startTime,
                                                                       String endTime, String benchmark,
                                                                       SelectValueBean open, SelectValueBean high, SelectValueBean close,
                                                                       SelectValueBean low, SelectValueBean volume, SelectValueBean adj_price) {

        ArrayList<BenchMarkInfoVO> result = getBenchMarkInfoByTimeOrByMarket(startTime,endTime,benchmark);

        if(null != open)
            result = selection(result.iterator(),open.getStart(),open.getEnd(),"Open");

        if(null != high)
            result = selection(result.iterator(),high.getStart(),high.getEnd(),"High");
        if(null != low)
            result = selection(result.iterator(),low.getStart(),low.getEnd(),"Low");
        if(null != close)
            result = selection(result.iterator(),close.getStart(),close.getEnd(),"Close");
        if(null != adj_price)
            result = selection(result.iterator(),adj_price.getStart(),adj_price.getEnd(),"Adj_price");
        if(null != volume)
            result = selection(result.iterator(),volume.getStart(),volume.getEnd(),"Volume");



        return result;
    }




    private ArrayList<BenchMarkInfoVO> selection(Iterator<BenchMarkInfoVO> it, double start, double end, String method) {
        method ="get"+method;
        ArrayList<BenchMarkInfoVO> result = new ArrayList<BenchMarkInfoVO>();
        while (it.hasNext()) {
            BenchMarkInfoVO tem = it.next();
            Class relection = tem.getClass();
            Method m = null;
            try {
                m = relection.getDeclaredMethod(method);
                double num =(Double) m.invoke(tem);
                if(num<=end&&num>=start) {

                    result.add(tem);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }


        return result;
    }
}
