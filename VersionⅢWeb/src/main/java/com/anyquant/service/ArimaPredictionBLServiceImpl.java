package com.anyquant.service;

import com.anyquant.dao.StockDao;
import com.anyquant.model.StockInfoPO;
import com.anyquant.utils.TimeUtil;
import com.anyquant.utils.arimaModel.arima.ARIMA;
import com.anyquant.vo.NowStockVo;
import com.anyquant.vo.StockPredictVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jiayiwu on 16/5/29.
 */
@Service
public class ArimaPredictionBLServiceImpl implements ArimaPredictionBLService {
    @Resource
    private StockDao stockDao;





    ArrayList<Double> currentDataList = new ArrayList<Double>();

    public ArrayList<StockPredictVo> getData(String stock, double days) throws Exception {
        initData(stock, days);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        double predict = makeModel();
        ArrayList<StockPredictVo> resultPOList = new ArrayList<StockPredictVo>();
        List<?>result =  stockDao.tailorCriteriaBetween(StockInfoPO.class,stock, TimeUtil.getPassedDate(days, TimeUtil.getNowDate()), TimeUtil.getNowDate());
        for(int i =0;i<result.size();i++){
            StockInfoPO stockInfoPO = (StockInfoPO)result.get(i);
            resultPOList.add(new StockPredictVo(formatter.format(new Date(stockInfoPO.getDate().getTime())),stockInfoPO.getClose()));
        }

        StockPredictVo stockPredictVo = new StockPredictVo(TimeUtil.getPassedDate(-1, TimeUtil.getNowDate()),predict);


        resultPOList.add(stockPredictVo);
        return resultPOList;
    }


    public StockPredictVo getYesterDayData(String stock, double days) throws Exception {
        initYesterDayData(stock,days);
        double predict = makeModel();
        return new StockPredictVo(TimeUtil.getPassedDate(-1, TimeUtil.getNowDate()),predict);
    }

    private void initData(String stock, double days) {
        currentDataList.clear();
        List<?>list =  stockDao.tailorCriteriaBetween (StockInfoPO.class,stock, TimeUtil.getPassedDate(days, TimeUtil.getNowDate()), TimeUtil.getNowDate());

        for (int i = 0; i < list.size(); i++) {
            currentDataList.add(((StockInfoPO)list.get(i)).getClose());
        }

    }

    private void initYesterDayData(String stock, double days) {
        currentDataList.clear();
        List<?>list =  stockDao.tailorCriteriaBetween (StockInfoPO.class,stock, TimeUtil.getPassedDate(days+1, TimeUtil.getNowDate()), TimeUtil.getPassedDate(-1, TimeUtil.getNowDate()));

        for (int i = 0; i < list.size(); i++) {
            currentDataList.add(((StockInfoPO)list.get(i)).getClose());
        }

    }

    private double makeModel() {
        double[] dataArray = new double[currentDataList.size()];
        for (int i = 0; i < currentDataList.size(); i++)
            dataArray[i] = currentDataList.get(i);



        ARIMA arima = new ARIMA(dataArray);
        int[] model = arima.getARIMAmodel();
        return arima.aftDeal(arima.predictValue(model[0], model[1]));

    }


}
