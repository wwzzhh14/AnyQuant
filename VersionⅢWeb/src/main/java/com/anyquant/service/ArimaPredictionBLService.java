package com.anyquant.service;


import com.anyquant.vo.StockPredictVo;

import java.util.ArrayList;

/**
 * Created by Jiayiwu on 16/5/29.
 */
public interface ArimaPredictionBLService {

    /*
   days:总参考天数
   type：price 0
      or volumn 1
    */
    public ArrayList<StockPredictVo> getData(String stock, double days) throws Exception;
    public StockPredictVo getYesterDayData(String stock, double days) throws Exception;
}
