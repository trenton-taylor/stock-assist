package com.taylor.stocks.dao.impl;

import com.taylor.stocks.client.YahooStockClient;
import com.taylor.stocks.domain.Stock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Map;

/**
 * This would normally be used to get/read/write stock values from a database. Mocking for sake of brevity
 */
@ExtendWith(MockitoExtension.class)
public class StockDAOInMemoryTest {

    @Mock
    YahooStockClient yahooStockClient;

    @InjectMocks
    StocksDAOInMemory stockDao;

    private static Map<String, Stock> stockDataMap;

    private static Stock stock1 = new Stock("TEST1", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0L);
    private static Stock stock2 = new Stock("TEST2", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0L);

    @BeforeAll
    public static void init() {
        stockDataMap.put("TEST1", stock1);
        stockDataMap.put("TEST2", stock2);
    }


//    @Test
//    public void testGetWatchedStockList(){
//        when()
//    }

//    public StockData getWatchedStockData(String symbol) throws StockException {
//        if (watchedStocks.get(symbol) == null) {
//            throw new StockException(symbol + " is not currently in your watch list.");
//        }
//        StockData result = watchedStocks.get(symbol);
//        return result.updateStockData(result.getCurrentPrice());
//    }
//
//    /**
//     * Returns a list of all watched stocks,
//     *
//     * @return List of all watched stocks
//     * @throws StockException if No Stock Data is found
//     */
//    public List<StockData> getWatchedStocksData() throws StockException {
//        if (watchedStocks.size() == 0) {
//            throw new StockException("No stocks found in your watch list.");
//        }
//        Map<String, Stock> rawStockData = yahooStockClient.getYahooStocks(getWatchedStocksList());
//
//        rawStockData.forEach((k, v) -> {
//            BigDecimal price = v.getQuote().getPrice();
//            watchedStocks.get(k).updateStockData(price);
//        });
//
//        return watchedStocks.values().stream().collect(Collectors.toList());
//
//    }
//
//    /**
//     * Adds a watched Stock to the in memory data store - Stock Data populated further up in the chain
//     *
//     * @param stock to add to list
//     * @throws StockException if stock is already being watched
//     */
//    public void addWatchedStock(StockData stock) throws StockException {
//        if (watchedStocks.containsKey(stock)) {
//            throw new StockException("Stock already being watched.");
//        } else {
//            watchedStocks.put(stock.getSymbol(), stock);
//        }
//    }
//
//    /**
//     * Deletes a watched stock from the list.
//     *
//     * @param stockSymbol
//     * @throws StockException if symbol not found in watch list
//     */
//    public void deleteWatchedStock(String stockSymbol) throws StockException {
//        if (watchedStocks.containsKey(stockSymbol)) {
//            watchedStocks.remove(stockSymbol);
//        } else {
//            throw new StockException("Could not find symbol " + stockSymbol + " to delete.");
//        }
//    }


}
