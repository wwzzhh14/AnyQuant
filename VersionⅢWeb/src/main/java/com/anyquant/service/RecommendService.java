package com.anyquant.service;

import com.anyquant.vo.HotStockVo;
import com.anyquant.vo.IndustryVo;

import java.util.List;

/**
 * Created by Jiayiwu on 16/5/31.
 */
public interface RecommendService {
    public List<HotStockVo> getHotStock();

    public List<IndustryVo> getIndustry(String startDate, int days);
}
