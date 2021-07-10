package com.taylor.stocks.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class StockTest {

    private Stock stock;

    @BeforeEach
    public void init(){
        stock = new Stock("TEST", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0L);
    }

    @Test
    public void testUpdateStockData(){

        Assertions.assertEquals(stock.getSymbol(), "TEST");

        Assertions.assertEquals(stock.getCurrentPrice(), BigDecimal.ZERO);
        Assertions.assertEquals(stock.getAveragePrice(), BigDecimal.ZERO);
        Assertions.assertEquals(stock.getSumPrice(), BigDecimal.ZERO);
        Assertions.assertEquals(stock.getQueryCount(), 0L);

        stock.updateStockData(BigDecimal.valueOf(1.0));

        Assertions.assertEquals(stock.getCurrentPrice(), BigDecimal.valueOf(1.0));
        Assertions.assertEquals(stock.getAveragePrice(), BigDecimal.valueOf(1.0));
        Assertions.assertEquals(stock.getSumPrice(), BigDecimal.valueOf(1.0));
        Assertions.assertEquals(stock.getQueryCount(), 1L);

        stock.updateStockData(BigDecimal.valueOf(11.0));

        Assertions.assertEquals(stock.getCurrentPrice(), BigDecimal.valueOf(11.0));
        Assertions.assertEquals(stock.getAveragePrice(), BigDecimal.valueOf(6.0));
        Assertions.assertEquals(stock.getSumPrice(), BigDecimal.valueOf(12.0));
        Assertions.assertEquals(stock.getQueryCount(), 2L);

    }

}
