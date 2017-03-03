package com.anyquant.service;

import com.anyquant.dao.StockDao;
import com.anyquant.dao.StockNameDao;
import com.anyquant.model.StockName;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Jiayiwu on 16/5/26.
 */
@Service
public class StockNameServiceImpl implements StockNameService {
    @Resource
    private StockNameDao stockNameDao;
    private static  List<StockName> stockNames;
    public String getAllStockName(String codeNum) {
        if(stockNames==null){
            stockNames=(List<StockName>)stockNameDao.getAllStockName();
        }
        for(int i = 0;i<stockNames.size();i++){
            StockName stockName = (StockName)stockNames.get(i);
            if(stockName.getCodeNum().contains(codeNum)||stockName.getStockName().contains(codeNum)){

                codeNum = stockName.getCodeNum();
                return codeNum;
            }
        }
        return null;
    }

    public List<StockName> getAllStockName() {
        if(stockNames==null){
            stockNames=(List<StockName>)stockNameDao.getAllStockName();
        }
        return stockNames;
    }
}
