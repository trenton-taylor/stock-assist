package com.taylor.stocks.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.math.BigDecimal;

@Slf4j
public class YahooStockClient {


    public static boolean isValidStock(String ticker){
        try{
            YahooFinance.get(ticker).getQuote();
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public static Stock getStock(String ticker){
        try{
            return YahooFinance.get(ticker);
        } catch(IOException ioe){
            log.error("Exception retrieving stock from Yahoo!");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Exception retrieving stock from Yahoo!");
        }
    }


    public static BigDecimal getPriceByTicker(String ticker){
        return getStock(ticker).getQuote().getPrice();
    }

    public static BigDecimal getPriceByStock(Stock stock){
            return stock.getQuote().getPrice();
    }

}
