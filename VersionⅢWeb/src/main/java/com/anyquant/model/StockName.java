package com.anyquant.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Jiayiwu on 16/5/26.
 */
@Entity
@Table(name = "stockName")
public class StockName {
    @Id
    String codeNum;
    String stockName;

    public StockName() {
    }

    public StockName(String codeNum, String stockName) {
        this.codeNum = codeNum;
        this.stockName = stockName;
    }

    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}
