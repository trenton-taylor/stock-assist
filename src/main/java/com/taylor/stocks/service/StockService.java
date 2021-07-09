package com.taylor.stocks.service;

import com.taylor.stocks.dao.StockDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class StockService {

    @Autowired
    private StockDAO stockList;

    public Set<String> getStocks(){
        return stockList.getStocks();
    }

    public void addStock(String stock){
        stockList.addStock(stock);
    }

    public void deleteStock(String stock){
        stockList.deleteStock(stock);
    }


}
