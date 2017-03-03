package businesslogicservice;


import config.ExchangePlace;
import vo.*;

import java.util.ArrayList;

/**
 * Created by HP on 2016/3/2.
 * modified by JiayiWu on 2016/3/5
 */
public interface StockInfoBLService {

    /**
      * @Title: getStockByYearOrByPlace
      * @Description: 返回选定的15只股票最近一天的信息（用于展示股票列表），具体股票名称参见config包中的StockName 显示所有股票信息调用这个接口
      *               调用示例  getStockByYearOrByPlace(2015,ExchangePlace.sh)
      * @param year
      * @param place
      * @return
      * ArrayList<StockInfoVO>
      * @throws
      */
    public ArrayList<StockInfoVO> getStockByYearOrByPlace(String year, ExchangePlace place);
//    返回某只股票的详细信息，默认为2016-01-01到2016-02-01
    public ArrayList<StockInfoVO> getStockInfo(String stock);
//     返回某只股票某段时间内的详细信息
    /**
      * @Title: getStockInfoByTime
      * @Description: 显示具体股票信息调用这个接口 调用示例 getStockInfoByTime(sh600888,2015-11-11,2015-12-12)
      *               注意日期必须用yyyy-MM-dd形式
      * @param stock
      * @param startTime
      * @param endTime
      * @return
      * ArrayList<StockInfoVO>
      * @throws
      */
    public ArrayList<StockInfoVO> getStockInfoByTime(String stock,String startTime,String endTime);

    //其他接口一样,选中的传一个SelectValueBean(double start,double end),如果用户没选中的则传null
    public ArrayList<StockInfoVO> getStockInfoBySelect(String stock, String startTime,String endTime,SelectValueBean open,SelectValueBean high,SelectValueBean low, SelectValueBean Close
,   SelectValueBean adj_price,SelectValueBean volume,SelectValueBean pe,SelectValueBean pb);

    public ArrayList<HistoryInfoVO> getHistoryInfo(String codeNum);

    public NowTimeStockInfoVO getNowTimeStockInfo(String codeNum);
    /*
    type:"DEA";"DIF";"MACD"
     */
    public ArrayList<macdVO> getMACDData(String startTime, String endTime, String codeNum, String type);

    public void update();

    public ArrayList<IncreasementVO> getStockInscreasment(String startTime,String endTime,String codeNum);

    /*
    days:5,10,20,30,60
     */
    public ArrayList<MAVO> getMA(int days,String startTime,String endTime,String codeNum);

}
