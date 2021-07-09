package com.taylor.stocks.domain;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class StockResult {

    public String ticker;
    public BigDecimal price;

}
