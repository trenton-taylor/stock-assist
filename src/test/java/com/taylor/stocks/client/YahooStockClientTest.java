package com.taylor.stocks.client;

import com.taylor.stocks.exception.StockException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;

import java.math.BigDecimal;
import java.util.*;

/**
 * Client class for getting data from Yahoo and formatting as needed.
 */
@ExtendWith(MockitoExtension.class)
public class YahooStockClientTest {


    @InjectMocks
    YahooStockClient yahooStockClient;

    @Test
    public void testGetYahooStockNull() throws Exception {
        try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {
            utilities.when(() -> YahooFinance.get(Mockito.anyString())).thenReturn(null);
            Assertions.assertThrows(StockException.class, () -> yahooStockClient.getYahooStock("TEST1"));
        }
    }

    @Test
    public void testGetYahooStockThrowsException() throws Exception {
        try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {
            utilities.when(() -> YahooFinance.get(Mockito.anyString())).thenThrow(new IOException());
            Assertions.assertThrows(StockException.class, () -> yahooStockClient.getYahooStock("TEST2"));
        }
    }

    @Test
    public void testGetYahooStock() throws Exception {
        try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {
            utilities.when(() -> YahooFinance.get(Mockito.anyString())).thenReturn(new yahoofinance.Stock("TEST"));
            Assertions.assertEquals(yahooStockClient.getYahooStock("TEST").getSymbol(), "TEST");
        }
    }


    @Test
    public void testGetYahooStocksNoData(){
        try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {
            utilities.when(() -> YahooFinance.get(Mockito.any(String[].class))).thenReturn(null);
            Assertions.assertEquals(yahooStockClient.getYahooStocks(new HashSet<>(Arrays.asList("TEST1", "TEST2"))), null);
        }
    }


    @Test
    public void testGetYahooStocks(){
        try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {

            Map<String, yahoofinance.Stock> testData = new HashMap<>();
            testData.put("TEST1", new yahoofinance.Stock("TEST2"));
            testData.put("TEST2", new yahoofinance.Stock("TEST2"));

            Set<String> symbols = new HashSet<>(Arrays.asList("TEST1", "TEST2"));

            utilities.when(() -> YahooFinance.get(Mockito.any(String[].class))).thenReturn(testData);
            Assertions.assertEquals(yahooStockClient.getYahooStocks(symbols).size(), 2);
        }
    }

    @Mock
    yahoofinance.Stock yahooStock;

    @Mock
    StockQuote stockQuote;

    @Test
    public void testGetStockData() throws Exception {
        try (MockedStatic<YahooFinance> utilities = Mockito.mockStatic(YahooFinance.class)) {

            utilities.when(() -> YahooFinance.get(Mockito.anyString())).thenReturn(yahooStock);
            Mockito.when(yahooStock.getQuote()).thenReturn(stockQuote);
            Mockito.when(stockQuote.getPrice()).thenReturn(BigDecimal.valueOf(1.23));

            Assertions.assertEquals(yahooStockClient.getStockData("TEST").getSymbol(), "TEST");
            Assertions.assertEquals(yahooStockClient.getStockData("TEST").getAveragePrice(), BigDecimal.valueOf(1.23));
            Assertions.assertEquals(yahooStockClient.getStockData("TEST").getCurrentPrice(), BigDecimal.valueOf(1.23));
            Assertions.assertEquals(yahooStockClient.getStockData("TEST").getSumPrice(), BigDecimal.ZERO);
            Assertions.assertEquals(yahooStockClient.getStockData("TEST").getQueryCount(), 0L);
        }
    }



}
