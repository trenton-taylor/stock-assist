package com.taylor.stocks.client;

import com.taylor.stocks.domain.Stock;
import com.taylor.stocks.exception.StockException;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * Client class for getting data from Yahoo and formatting as needed.
 */
@Service
public class YahooStockClient {

    /**
     * Wrapper for getting raw Stock data from Yahoo
     * @param symbol
     * @return
     * @throws StockException
     */
    public yahoofinance.Stock getYahooStock(String symbol) throws StockException{
        try{
            yahoofinance.Stock result = YahooFinance.get(symbol);
            if(result == null){
                throw new StockException("Stock not found in Yahoo! API.");
            }
            return result;
        } catch(IOException e){
            throw new StockException("Exception retrieving stock from Yahoo! API");
        }
    }

    /**
     * Gets a list of Yahoo stocks
     * @param symbols
     * @return
     */
    public Map<String, yahoofinance.Stock> getYahooStocks(Set<String> symbols){
        try{
            return YahooFinance.get(symbols.toArray(String[]::new));
        } catch(IOException ioe){
            throw new StockException("Exception retrieving stocks from Yahoo! API");
        }
    }

    /**
     * Gets the raw stock data from Yahoo and converts it to our StockData format
     * @param symbol
     * @return StockData in internal format
     */
    public Stock getStockData(String symbol) {
        yahoofinance.Stock stock = getYahooStock(symbol);
        BigDecimal price = stock.getQuote().getPrice();
        return new Stock(symbol, price, price, BigDecimal.ZERO, 0L);
    }

    /**
     * Gets just the raw price data from Yahoo
     * @param symbol to get data for
     * @return BigDecimal of price
     */
    public BigDecimal getCurrentPriceBySymbol(String symbol){
        return getYahooStock(symbol).getQuote().getPrice();
    }

}
