package com.taylor.stocks.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockException extends RuntimeException {
    public StockException(String message){
        super(message);
    }
}
