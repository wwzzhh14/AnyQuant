package com.anyquant.service;

import com.anyquant.config.State;
import com.anyquant.config.StringMessage;
import com.anyquant.dao.MarketDao;
import com.anyquant.model.BenchMarkInfoPO;
import com.anyquant.model.NowTimeBenchMarkInfoPO;
import com.anyquant.utils.JsonAnalysisUtil;
import com.anyquant.utils.JsonDataUtil;
import com.anyquant.utils.TimeUtil;
import com.anyquant.utils.arimaModel.arima.ARIMA;
import com.anyquant.vo.MarkHomeVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jiayiwu on 16/5/31.
 */
@Service
public class MarketServiceImpl implements MarketService {
    @Resource
    MarketDao marketDao;
    private MarkHomeVo markHomeVo = new MarkHomeVo();
    private final double thRate = 0.05;

    private ArrayList<ArrayList<Double>> dataList = new ArrayList<ArrayList<Double>>();
    private List<BenchMarkInfoPO> poList;
    private ArrayList<Double> closeDataList = new ArrayList<Double>();
    private ArrayList<Double> highDataList = new ArrayList<Double>();
    private ArrayList<Double> lowDataList = new ArrayList<Double>();
    private double days = 60;

    public List<BenchMarkInfoPO> getBenchMarkInfo(String start, String end) {

        return marketDao.getBenchMarkInfo(start,end);
    }

    public NowTimeBenchMarkInfoPO getnowMatket() {
        return marketDao.getNowtimeBnech();
    }

    public MarkHomeVo getMarketHome(){
        initData(days);
        makeModel();
        calDistance();
        return markHomeVo;
    }

    private void calDistance(){
        BenchMarkInfoPO yes = poList.get(poList.size()-1);
        double yesClose = yes.getClose();
        double yesHigh = yes.getHigh();
        double yesLow = yes.getLow();

        double yesClosePre = markHomeVo.getYestDayClosePre();
        double yesHighPre = markHomeVo.getYestDayHigPre();
        double yesLowPre = markHomeVo.getYestDayLowPre();

        markHomeVo.setYestDayClose(yesClose);
        markHomeVo.setYestDayMax(yesHigh);
        markHomeVo.setYestDaymin(yesLow);

        markHomeVo.setDistanceClose(((yesClosePre-yesClose)/yesClose)*100);
        markHomeVo.setDistanceHigh(((yesHighPre-yesHigh)/yesHigh)*100);
        markHomeVo.setDistanceLow(((yesLowPre-yesLow)/yesLow)*100);

    }



//    public ArrayList<BenchMarkInfoVo> getData(double days) throws Exception {
//
//
//
//        ArrayList<BenchMarkInfoVo> resultPOList = new StockNetImpl().getStockInfoByTime(stock, TimeUtil.getPassedDate(days, TimeUtil.getNowDate()), TimeUtil.getNowDate());
//        BenchMarkInfoVo predictPrice = new BenchMarkInfoVo(0, 0, 0, 0, 0, predict, 0, 0, 0, TimeUtil.getPassedDate(-1, TimeUtil.getNowDate()));
//        resultPOList.add(predictPrice);
//        ArrayList<BenchMarkInfoVO> resultList = poToVO(resultPOList, stock);
//        return resultList;
//
//    }

    private void initData(double days) {

        NowTimeBenchMarkInfoPO nowTimeBenchMarkInfoPO = marketDao.getNowtimeBnech();//这个获取当天的大盘数据
        markHomeVo.setTodayMin(nowTimeBenchMarkInfoPO.getLowpri());
        markHomeVo.setTodayMax(nowTimeBenchMarkInfoPO.getHighPri());
        markHomeVo.setTodayClose(nowTimeBenchMarkInfoPO.getNowpri());

        closeDataList.clear();
        highDataList.clear();
        lowDataList.clear();
        poList =marketDao.getBenchMarkInfo( TimeUtil.getPassedDate(days, TimeUtil.getNowDate()), TimeUtil.getPassedDate(1,TimeUtil.getNowDate()));
        for (int i = 0; i < poList.size(); i++) {
            BenchMarkInfoPO temp = poList.get(i);
            closeDataList.add(temp.getClose());
            highDataList.add(temp.getHigh());
            lowDataList.add(temp.getLow());
        }

//        System.out.println(currentDataList.size());
    }

    private void makeModel() {
        double[] todayClosePre = new double[closeDataList.size()];
        double[] yesClosePre = new double[closeDataList.size()-1];
        double[] todayHighPre = new double[highDataList.size()];
        double[] yesHighPre = new double[highDataList.size()-1];
        double[] todayLowPre = new double[lowDataList.size()];
        double[] yesLowPre = new double[lowDataList.size()-1];

        for (int i = 0; i < closeDataList.size(); i++)
            todayClosePre[i] = closeDataList.get(i);
        for (int i = 0; i < closeDataList.size()-1; i++)
            yesClosePre[i] = todayClosePre[i];

        for (int i = 0; i < highDataList.size(); i++)
            todayHighPre[i] = highDataList.get(i);
        for (int i = 0; i < highDataList.size()-1; i++)
            yesHighPre[i] = todayHighPre[i];

        for (int i = 0; i < lowDataList.size(); i++)
            todayLowPre[i] = lowDataList.get(i);
        for (int i = 0; i < lowDataList.size()-1; i++)
            yesLowPre[i] = todayLowPre[i];

//        System.out.println(currentDataList.size());

        ARIMA arima = new ARIMA(todayClosePre);
        int[] model = arima.getARIMAmodel();
        markHomeVo.setClosePre(arima.aftDeal(arima.predictValue(model[0], model[1])));


        arima = new ARIMA(yesClosePre);
        model = arima.getARIMAmodel();
        markHomeVo.setYestDayClosePre(arima.aftDeal(arima.predictValue(model[0], model[1])));

        arima = new ARIMA(todayHighPre);
        model = arima.getARIMAmodel();
        markHomeVo.setHighPre(arima.aftDeal(arima.predictValue(model[0], model[1])));

        arima = new ARIMA(yesHighPre);
        model = arima.getARIMAmodel();
        markHomeVo.setYestDayHigPre(arima.aftDeal(arima.predictValue(model[0], model[1])));

        arima = new ARIMA(todayLowPre);
        model = arima.getARIMAmodel();
        markHomeVo.setLowPre(arima.aftDeal(arima.predictValue(model[0], model[1])));

        arima = new ARIMA(yesLowPre);
        model = arima.getARIMAmodel();
        markHomeVo.setYestDayLowPre(arima.aftDeal(arima.predictValue(model[0], model[1])));


//        System.out.println("Best model is [p,q]=" + "[" + model[0] + " " + model[1] + "]");
//        System.out.println("Predict value=" + arima.aftDeal(arima.predictValue(model[0], model[1])));
//        System.out.println("Predict error=" + (arima.aftDeal(arima.predictValue(model[0], model[1])) - currentDataList.get(currentDataList.size() - 1)) / currentDataList.get(arraylist.size() - 1) * 100 + "%");
    }

//    private ArrayList<StockInfoVO> poToVO(ArrayList<StockInfoPO> poList, String stockCode) {
//        HashMap<String, String> stockMap = StockName.getStockNameAndCode();
//        ArrayList<StockInfoVO> voList = new ArrayList<StockInfoVO>();
//        for (StockInfoPO po : poList) {
//            if (po != null) {
//                voList.add(new StockInfoVO(stockMap.get(stockCode), stockCode, po.getPb(), po.getPe_ttm(), po.getTurnover(), po.getVolume(),
//                        po.getAdj_price(), po.getClose(), po.getLow(), po.getHigh(), po.getOpen(), po.getDate()));
//            }
//        }
//        return voList;
//    }
}
