package com.taylor.stocks.exception;

import com.taylor.stocks.domain.ServiceResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Customized exception handler for handling various custom exceptions
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value= { StockException.class })
    protected ResponseEntity<Object> handleStockException(RuntimeException ex, WebRequest request) {
        ServiceResponse response = new ServiceResponse(HttpStatus.BAD_REQUEST.value(), "Service Exception: " + ex.getMessage());
        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
