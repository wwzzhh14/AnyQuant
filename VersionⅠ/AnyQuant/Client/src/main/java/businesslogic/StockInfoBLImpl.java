package businesslogic;

import businesslogicservice.StockInfoBLService;
import config.ExchangePlace;
import config.StockName;
import net.StockNetImpl;
import netservice.StockNetService;
import po.StockInfoPO;
import vo.SelectValueBean;
import vo.StockInfoVO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by HP on 2016/3/6.
 * Modified by Jiayiwu on 2016/3/11
 */
public class StockInfoBLImpl implements StockInfoBLService {

    private StockNetService stockNet = new StockNetImpl();
    private String nowStockNum="sh600000";

    public ArrayList<StockInfoVO> getStockByYearOrByPlace(String year, ExchangePlace place) {
        ArrayList<StockInfoPO> poList = stockNet.getStockByYearOrByPlace(year, place);
        if (poList != null) {
            ArrayList<StockInfoVO> voList = new ArrayList<StockInfoVO>();
            if (poList.size() > 0) {
                Object[] stockcode = StockName.getStockNameAndCode().keySet().toArray();
                voList = poToVO(poList, stockcode);
            }
            Collections.reverse(voList);
            return voList;
        }

        return null;
    }

    public ArrayList<StockInfoVO> getStockInfo(String stock) {

        nowStockNum = stock;

        ArrayList<StockInfoPO> poList = stockNet.getStockInfo(stock);
        if (poList != null) {
            ArrayList<StockInfoVO> voList = new ArrayList<StockInfoVO>();
            if (poList.size() > 0) {
                voList = poToVO(poList, stock);
            }

            Collections.reverse(voList);
            return voList;
        }
        return null;
    }

    public ArrayList<StockInfoVO> getStockInfoByTime(String stock, String startTime, String endTime) {

        nowStockNum = stock;

        ArrayList<StockInfoPO> poList = stockNet.getStockInfoByTime(stock, startTime, endTime);
        if (poList != null) {
            ArrayList<StockInfoVO> voList = new ArrayList<StockInfoVO>();
            if (poList.size() > 0) {
                voList = poToVO(poList, stock);
            }

            Collections.reverse(voList);
            return voList;
        }
        return null;
    }


    private ArrayList<StockInfoVO> poToVO(ArrayList<StockInfoPO> poList, Object[] stockCodes) {
        HashMap<String, String> stockMap = StockName.getStockNameAndCode();
        ArrayList<StockInfoVO> voList = new ArrayList<StockInfoVO>();
        for (int i = 0; i < stockCodes.length; i++) {
            StockInfoPO po = (StockInfoPO) poList.get(i);
            if (po != null) {
                voList.add(new StockInfoVO(stockMap.get((String) stockCodes[i]), (String) stockCodes[i], po.getPb(), po.getPe_ttm(), po.getTurnover(), po.getVolume(),
                        po.getAdj_price(), po.getClose(), po.getLow(), po.getHigh(), po.getOpen(), po.getDate()));
            }
        }
        return voList;
    }

    private ArrayList<StockInfoVO> poToVO(ArrayList<StockInfoPO> poList, String stockCode) {
        HashMap<String, String> stockMap = StockName.getStockNameAndCode();
        ArrayList<StockInfoVO> voList = new ArrayList<StockInfoVO>();
        for (StockInfoPO po : poList) {
            if (po != null) {
                voList.add(new StockInfoVO(stockMap.get(stockCode), stockCode, po.getPb(), po.getPe_ttm(), po.getTurnover(), po.getVolume(),
                        po.getAdj_price(), po.getClose(), po.getLow(), po.getHigh(), po.getOpen(), po.getDate()));
            }
        }
        return voList;
    }


    public ArrayList<StockInfoVO> getStockInfoBySelect(String stock, String startTime, String endTime, SelectValueBean open, SelectValueBean high, SelectValueBean low, SelectValueBean close, SelectValueBean adj_price, SelectValueBean volume, SelectValueBean pe, SelectValueBean pb) {

        ArrayList<StockInfoVO> result = getStockInfoByTime(stock,startTime,endTime);

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
        if(null != pe)
            result = selection(result.iterator(),pe.getStart(),pe.getEnd(),"Pe");
        if(null != pb)
            result = selection(result.iterator(),pb.getStart(),pb.getEnd(),"Pb");


        return result;
    }



    private ArrayList<StockInfoVO> selection(Iterator<StockInfoVO> it, double start, double end,String method) {
        method ="get"+method;
        ArrayList<StockInfoVO> result = new ArrayList<StockInfoVO>();
        while (it.hasNext()) {
            StockInfoVO tem = it.next();
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
