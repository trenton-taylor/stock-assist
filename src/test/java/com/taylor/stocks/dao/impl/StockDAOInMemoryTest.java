package com.taylor.stocks.dao.impl;

import com.taylor.stocks.client.YahooStockClient;
import com.taylor.stocks.domain.Stock;
import com.taylor.stocks.exception.StockException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This would normally be used to get/read/write stock values from a database. Mocking for sake of brevity
 */
@ExtendWith(MockitoExtension.class)
public class StockDAOInMemoryTest {

    @InjectMocks
    StocksDAOInMemory stockDao;

    private static Map<String, Stock> stockDataMap;

    private static Stock stock1 = new Stock("TEST1", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0L);
    private static Stock stock2 = new Stock("TEST2", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0L);

    @BeforeAll
    public static void init() {
        stockDataMap = new HashMap<>();
        stockDataMap.put("TEST1", stock1);
        stockDataMap.put("TEST2", stock2);
    }

    @BeforeEach
    public void deleteAll(){
        stockDao.setWatchedStocks(new HashMap<>());
    }

    @Test
    public void testAddWatchedStock(){
        // Check empty
        Assertions.assertThrows(StockException.class, () -> stockDao.getWatchedStock("TEST"));
        // Add
        stockDao.addWatchedStock(stock1);
        // Check it's present
        Assertions.assertEquals(stockDao.getWatchedStock("TEST1").getSymbol(), stock1.getSymbol());
    }


    @Test
    public void testDeleteWatchedStock(){
        // Check empty
        Assertions.assertThrows(StockException.class, () -> stockDao.getWatchedStock("TEST"));
        // Add
        stockDao.addWatchedStock(stock1);
        // Check it's present
        Assertions.assertEquals(stockDao.getWatchedStock("TEST1").getSymbol(), stock1.getSymbol());
        // Check for bad removal logic
        Assertions.assertThrows(StockException.class, () -> stockDao.getWatchedStock("BADSTOCK"));
        // Remove valid one
        stockDao.deleteWatchedStock("TEST1");
        // Check valid one no longer exists
        Assertions.assertThrows(StockException.class, () -> stockDao.getWatchedStock("TEST1"));
    }




}
