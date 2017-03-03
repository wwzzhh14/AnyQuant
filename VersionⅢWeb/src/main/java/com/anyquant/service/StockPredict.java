package com.anyquant.service;

import com.anyquant.vo.StockPredictVo;

import java.util.ArrayList;

/**
 * Created by Jiayiwu on 16/5/28.
 */
public interface StockPredict {
    public ArrayList<StockPredictVo> getSimilarData(String stock, double days, int width) throws Exception;
}
