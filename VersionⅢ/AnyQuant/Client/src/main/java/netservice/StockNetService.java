package netservice;

import config.ExchangePlace;
import po.NowTimeStockInfoPO;
import po.StockInfoPO;

import java.util.ArrayList;

/**
 * Created by HP on 2016/3/2.
 */
public interface StockNetService {

    public ArrayList<StockInfoPO> getStockByYearOrByPlace(String year, ExchangePlace place);

    public ArrayList<StockInfoPO> getStockInfo(String stock);

    public ArrayList<StockInfoPO> getStockInfoByTime(String stock,String startTime,String endTime);

    public NowTimeStockInfoPO getNowTimeStockInfo(String codeNum);
}
