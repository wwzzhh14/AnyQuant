package com.anyquant.dao;

import com.anyquant.model.StockInfoPO;
import com.anyquant.model.StockName;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Jiayiwu on 16/5/26.
 */
@Repository
public class StockNameDao {

    @Resource
    private BaseDao baseDao;

    public List<?> getAllStockName(){
        return baseDao.getAll(StockName.class);
    }
}
