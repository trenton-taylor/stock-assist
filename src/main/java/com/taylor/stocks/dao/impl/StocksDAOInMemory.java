package com.taylor.stocks.dao.impl;

import com.taylor.stocks.dao.StocksDAO;
import com.taylor.stocks.domain.Stock;
import com.taylor.stocks.exception.StockException;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This would normally be used to get/read/write stock values from a database. Mocking for sake of brevity
 */
@Repository
public class StocksDAOInMemory implements StocksDAO<Stock> {

    private HashMap<String, Stock> watchedStocks;

    @PostConstruct
    public void init(){
        watchedStocks = new HashMap<>(); //init during server startup so it's not null
    }

    public HashMap<String, Stock> getWatchedStocks(){
        return watchedStocks;
    }

    /**
     * Gets the Watched stock list in Set form
     * @return the key set of the watched stocks
     */
    public Set<String> getWatchedStockSymbols(){
        return getWatchedStocks().keySet();
    }

    /**
     * Returns the watched StockData given the passed in symbol
     * @param symbol
     * @return StockData data for particular symbol
     * @throws StockException when no stock data is found
     */
    public Stock getWatchedStock(String symbol) throws StockException {
        if(watchedStocks.get(symbol) == null){
            throw new StockException(symbol + " is not currently in your watch list.");
        }
        return watchedStocks.get(symbol);
    }

    /**
     * Returns a list of all watched stocks,
     * @return List of all watched stocks
     * @throws StockException if No Stock Data is found
     */
    public List<Stock> getWatchedStocksList() throws StockException {
        if(watchedStocks.size() == 0){
            throw new StockException("No stocks found in your watch list.");
        }
        return watchedStocks.values().stream().collect(Collectors.toList());

    }

    /**
     * Adds a watched Stock to the in memory data store - Stock Data populated further up in the chain
     * @param stock to add to list
     * @throws StockException if stock is already being watched
     */
    public void addWatchedStock(Stock stock) throws StockException {
        if(watchedStocks.containsKey(stock.getSymbol())) {
            throw new StockException("Stock " + stock.getSymbol() + " is already being watched.");
        } else {
            watchedStocks.put(stock.getSymbol(), stock);
        }
    }

    /**
     * Deletes a watched stock from the list.
     * @param stockSymbol
     * @throws StockException if symbol not found in watch list
     */
    public void deleteWatchedStock(String stockSymbol) throws StockException {
        if(watchedStocks.containsKey(stockSymbol)){
            watchedStocks.remove(stockSymbol);
        } else {
            throw new StockException("Could not find symbol " + stockSymbol + " to delete.");
        }
    }



}
