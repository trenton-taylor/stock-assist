package com.taylor.stocks.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.math3.util.Precision;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Stock {

    private String symbol;
    private BigDecimal currentPrice;
    private BigDecimal averagePrice;

    @JsonIgnore
    private BigDecimal sumPrice; // for determining average, don't show in response

    private Long queryCount;

    /**
     * Updates the stock data with the new current price, and also updates average and query count
     * @param newCurrentPrice The new price of the stock retrieved from the external API
     * @return the updated stock
     */
    public Stock updateStockData(BigDecimal newCurrentPrice){

        if(sumPrice == null) { // failsafe to prevent NPE (shouldn't happen)
            sumPrice = BigDecimal.ZERO;
        }
        if(averagePrice == null){ // failsafe to prevent NPE (shouldn't happen)
            averagePrice = BigDecimal.ZERO;
        }
        if(queryCount == null || queryCount < 0) { //failsafe to prevent divide by 0
            queryCount = 0L;
        }

        currentPrice = newCurrentPrice;
        sumPrice = sumPrice.add(newCurrentPrice);
        queryCount++;
        averagePrice = BigDecimal.valueOf(Precision.round((sumPrice.doubleValue() / queryCount.doubleValue()), 2));

        return this;
    }

}
