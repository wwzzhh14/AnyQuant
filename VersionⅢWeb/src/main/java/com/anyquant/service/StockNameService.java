package com.anyquant.service;

import com.anyquant.model.StockName;

import java.util.List;

/**
 * Created by Jiayiwu on 16/5/26.
 */
public interface StockNameService {

    public String getAllStockName(String codeNum);
    public List<StockName> getAllStockName();
}
