package com.taylor.stocks.dao;

import com.taylor.stocks.exception.StockException;

import java.util.List;
import java.util.Set;

/**
 *  Interface for implementing a stock DAO
 * @param <T> The resulting stock data type (customizable using generics)
 */
public interface StocksDAO<T> {

    Set<String> getWatchedStockSymbols();

    T getWatchedStock(String symbol) throws StockException;
    List<T> getWatchedStocksList() throws StockException;

    void addWatchedStock(T stock) throws StockException;
    void deleteWatchedStock(String symbol) throws StockException;

}
