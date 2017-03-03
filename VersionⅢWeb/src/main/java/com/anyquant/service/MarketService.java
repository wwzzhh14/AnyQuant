package com.anyquant.service;

import com.anyquant.model.BenchMarkInfoPO;
import com.anyquant.model.NowTimeBenchMarkInfoPO;
import com.anyquant.vo.MarkHomeVo;

import java.util.List;

/**
 * Created by Jiayiwu on 16/5/31.
 */
public interface MarketService {

    public List<BenchMarkInfoPO> getBenchMarkInfo(String start,String end);
    public MarkHomeVo getMarketHome();
    public NowTimeBenchMarkInfoPO getnowMatket();
}
