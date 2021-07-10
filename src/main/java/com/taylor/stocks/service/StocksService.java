package com.taylor.stocks.service;

import com.taylor.stocks.client.YahooStockClient;
import com.taylor.stocks.dao.impl.StocksDAOInMemory;
import com.taylor.stocks.domain.Stock;
import com.taylor.stocks.exception.StockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class StocksService {

    private final StocksDAOInMemory stockDao;
    private final YahooStockClient yahooStockClient;

    @Autowired
    private StocksService(StocksDAOInMemory stockDao, YahooStockClient yahooStockClient){
        this.stockDao = stockDao;
        this.yahooStockClient = yahooStockClient;
    }

    /**
     * Returns just a list of the watched stocks without the stock data
     * @return
     */
    public Set<String> getWatchedStocksList(){
        if(stockDao.getWatchedStockSymbols() == null || stockDao.getWatchedStockSymbols().size() == 0){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There are no stocks currently being watched.");
        }
        return stockDao.getWatchedStockSymbols();
    }

    /**
     * Returns the stock data from the watch list
     * @param symbol
     * @return
     */
    public Stock getWatchedStockData(String symbol){
        Stock result = stockDao.getWatchedStock(symbol);
        result.updateStockData(yahooStockClient.getCurrentPriceBySymbol(symbol));
        return result;
    }

    /**
     * Returns data for all stocks in the watched list
     * @return
     */
    public List<Stock> getWatchedStocksData(){

        Map<String, yahoofinance.Stock> rawStockData = yahooStockClient.getYahooStocks(getWatchedStocksList());

        rawStockData.forEach((k,v) -> {
            BigDecimal updatedCurrentPrice = v.getQuote().getPrice();
            stockDao.getWatchedStocks().get(k).updateStockData(updatedCurrentPrice);
        });

        return stockDao.getWatchedStocksList();
    }

    /**
     * Adds a stock to the stock DAO watches
     * @param symbol
     */
    public void addStockToWatches(String symbol){
        if(stockDao.getWatchedStockSymbols().contains(symbol)){
            throw new StockException("Stock symbol " + symbol + " already found in you watch list.");
        }
        stockDao.addWatchedStock(yahooStockClient.getStockData(symbol));
    }

    /**
     * Adds a stock to the stock DAO watches
     * @param symbols
     */
    public void addStocksToWatches(List<String> symbols){
        symbols.forEach((s) -> {
            try{
                addStockToWatches(s);
            } catch (StockException se){
                //ignore
            }
        });
    }

    /**
     * Deletes a stock from the stock DAO watches
     * @param stock
     */
    public void deleteStockFromWatches(String stock){
        stockDao.deleteWatchedStock(stock);
    }


    /**
     * Deletes a stock from the stock DAO watches
     * @param symbols to delete
     */
    public void deleteStocksFromWatches(List<String> symbols){
        symbols.forEach((s) -> {
            try{
                deleteStockFromWatches(s);
            } catch (StockException se){
                //ignore
            }
        });
    }


}
