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

}
