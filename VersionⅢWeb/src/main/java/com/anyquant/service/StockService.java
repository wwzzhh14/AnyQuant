package com.anyquant.service;

import com.anyquant.model.NowTimeStockInfoPO;
import com.anyquant.model.StockInfoPO;
import com.anyquant.vo.RecommendVO;

import java.util.List;

/**
 * Created by Jiayiwu on 16/5/26.
 */
public interface StockService {

    public List<?> tailorCriteriaBetween(String codeNum,String start,String end);

    public List<?>getStockLength(int up,int end);

    public List<?>getAllStock();

    public NowTimeStockInfoPO getNowTimeStockInfo(String codeNum);

    public RecommendVO getRecommendVoByPython(String stockName);


}
