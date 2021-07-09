package com.taylor.stocks.routes;

import com.taylor.stocks.client.YahooStockClient;
import com.taylor.stocks.domain.StockResult;
import com.taylor.stocks.error.StockException;
import com.taylor.stocks.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("stocks")
public class Stocks {

    @Autowired
    StockService stockService;

    @GetMapping("all/current")
    public Map<String, Stock> getAllStockData() throws Exception {
        try {
            Map<String,Stock> x = YahooFinance.get(stockService.getStocks().toArray(String[]::new));
            return x;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No stocks found");
        }
    }

//    @GetMapping("all/average")
//    public StockResult getAllAverageStockData(@RequestParam("interval") String interval)) throws Exception {
//        try {
//            return new StockResult(ticker, YahooStockClient.getPriceByTicker(ticker));
//        } catch (Exception e){
//            String errorMsg = "Could not find stock symbol " + ticker;
//            log.error(errorMsg);
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
//        }
//    }

    @GetMapping("single/{ticker}/current")
    public StockResult getSingleCurrentStockData(@PathVariable String ticker) throws Exception {
//        try {
        return new StockResult(ticker, YahooStockClient.getPriceByTicker(ticker));
//        } catch (Exception e){
//            String errorMsg = "Could not find stock symbol " + ticker;
//            log.error(errorMsg);
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
//        }

    }

    @GetMapping("single/{ticker}/average")
    public StockResult getSingleAverageStockData(@PathVariable String ticker,
                                                 @RequestParam("interval") String interval,
                                                 @RequestParam("startDate") String startDate,
                                                 @RequestParam("endDate") String endDate) throws Exception {
//        try {
        return new StockResult(ticker, YahooStockClient.getPriceByTicker(ticker));
//        } catch (Exception e){
//            String errorMsg = "Could not find stock symbol " + ticker;
//            log.error(errorMsg);
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
//        }

    }

    @GetMapping("all")
    public Set<String> getAllStocks() throws Exception {
        return stockService.getStocks();
    }

    @PutMapping("{stock}")
    public String addStock(@PathVariable String stock){
        stockService.addStock(stock);
        return "Adding stock " + stock;
    }

    @DeleteMapping("{stock}")
    public String deleteStock(@PathVariable String stock){
        stockService.deleteStock(stock);
        return "Removing stock " + stock;
    }

}
