package com.taylor.stocks.routes;

import com.taylor.stocks.domain.ServiceResponse;
import com.taylor.stocks.domain.Stock;
import com.taylor.stocks.service.StocksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("stocks")
public class StocksRoute {

    private final StocksService stocksService;

    @Autowired
    public StocksRoute(StocksService stocksService){
        this.stocksService = stocksService;
    }

    /**
     * Returns all the stock data for stocks in the current watch list
     * @return
     */
    @GetMapping("multi")
    public List<Stock> getWatchedStocksData() {
        log.info("Requesting all stocks in your watch list");
        return stocksService.getWatchedStocksData();
    }

    /**
     * Adds stocks to the watch list (multiple)
     * @param symbols
     * @return
     */
    @PutMapping("multi")
    public ServiceResponse addStocksToWatches(@RequestBody List<String> symbols) {
        log.info("Adding " + symbols.toString() + " to your watch list");
        stocksService.addStocksToWatches(symbols);
        return new ServiceResponse(200, "Successfully added " + symbols.toString() + " to your watch list.");
    }

    /**
     * Deletes stocks from the watch list (multiple)
     * @param symbols
     * @return
     */
    @DeleteMapping("multi")
    public ServiceResponse deleteStocksFromWatches(@RequestBody List<String> symbols) {
        log.info("Deleting watches (multi)");
        stocksService.deleteStocksFromWatches(symbols);
        return new ServiceResponse(200, "Successfully deleted " + symbols.toString() + " from your watch list.");
    }

    /**
     * Gets a stock in the watch list
     * @param symbol
     * @return
     */
    @GetMapping("single/{symbol}")
    public Stock getSingleCurrentStockData(@PathVariable String symbol) {
        log.info("Requesting " + symbol + " from the watch list");
        return stocksService.getWatchedStockData(symbol);
    }

    /**
     * Puts a single stock in the watch list
     * @param symbol
     * @return
     */
    @PutMapping("single/{symbol}")
    public ServiceResponse addStockToWatches(@PathVariable String symbol){
        log.info("Adding " + symbol + " to the watch list");
        stocksService.addStockToWatches(symbol);
        return new ServiceResponse(200, "Successfully added " + symbol + " to your watch list.");
    }

    /**
     * Deletes a single stock from the watch list
     * @param symbol
     * @return
     */
    @DeleteMapping("single/{symbol}")
    public ServiceResponse deleteStockFromWatches(@PathVariable String symbol){
        log.info("Deleting " + symbol + " from the watch list");
        stocksService.deleteStockFromWatches(symbol);
        return new ServiceResponse(200, "Successfully deleted " + symbol + " from your watch list.");
    }

}
