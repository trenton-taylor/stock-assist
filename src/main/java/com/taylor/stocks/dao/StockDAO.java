package com.taylor.stocks.dao;

import com.taylor.stocks.client.YahooStockClient;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * This would normally be used to get/read/write stock values from a database. Mocking for sake of brevity
 */
@Component
public class StockDAO {

    private Set<String> stocks;

    @PostConstruct
    public void init(){
        stocks = new HashSet<>(); //init during server startup so it's not null
    }

    public Set<String> getStocks(){
        return stocks;
    }

    public void addStock(String stock){
        if(stocks.contains(stock)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock already being watched!");
        } else if (!YahooStockClient.isValidStock(stock)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid stock!");
        } else {
            stocks.add(stock);
        }
    }

    public void deleteStock(String stock){
        if(stocks.contains(stock)){
            stocks.remove(stock);
        }
    }



}
