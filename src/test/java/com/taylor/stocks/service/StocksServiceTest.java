package com.taylor.stocks.service;

import com.taylor.stocks.client.YahooStockClient;
import com.taylor.stocks.dao.impl.StocksDAOInMemory;
import com.taylor.stocks.domain.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class StocksServiceTest {

    @Mock
    StocksDAOInMemory stockDao;

    @Mock
    YahooStockClient yahooStockClient;

    @InjectMocks
    StocksService stocksService;

    @Test
    public void testNullStockList(){
        Mockito.when(stockDao.getWatchedStockSymbols()).thenReturn(null);
        Assertions.assertThrows(ResponseStatusException.class, () -> stocksService.getWatchedStocksList());
    }

    @Test
    public void testEmptyStockList(){
        Mockito.when(stockDao.getWatchedStockSymbols()).thenReturn(new HashSet<>());
        Assertions.assertThrows(ResponseStatusException.class, () -> stocksService.getWatchedStocksList());
    }

    @Test
    public void testGetWatchedStockList(){
        Mockito.when(stockDao.getWatchedStockSymbols()).thenReturn(Collections.singleton("GOOG"));
        Assertions.assertTrue(stocksService.getWatchedStocksList().contains("GOOG"));
        Assertions.assertFalse(stocksService.getWatchedStocksList().contains("AAPL"));
    }

    @Test
    public void testGetWatchedStockData(){
        Stock testStock = new Stock("TEST", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0L);
        Mockito.when(stockDao.getWatchedStock(Mockito.anyString())).thenReturn(testStock);
        Mockito.when(yahooStockClient.getCurrentPriceBySymbol(Mockito.anyString())).thenReturn(BigDecimal.TEN);

        Stock updatedStock = stocksService.getWatchedStockData("TEST");
        Assertions.assertEquals(updatedStock.getSymbol(), "TEST");
        Assertions.assertEquals(updatedStock.getCurrentPrice(), BigDecimal.TEN);
        Assertions.assertEquals(updatedStock.getSumPrice(), BigDecimal.TEN);
        Assertions.assertEquals(updatedStock.getAveragePrice(), BigDecimal.valueOf(10.0));
    }

}
