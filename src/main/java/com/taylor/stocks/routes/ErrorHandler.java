package com.taylor.stocks.routes;

import com.taylor.stocks.domain.StockResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ErrorHandler implements ErrorController {

    @ResponseBody
    @RequestMapping("error")
    public StockResponse handleRuntimeException(HttpServletRequest request) {

        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        String exception;
        if (statusCode == 404) {
            exception = "Route not found.";
        } else if (statusCode == 500) {
            exception = "Internal Server Error.";
        } else {
            exception = String.valueOf(request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
        }

        StockResponse response = new StockResponse(statusCode, "Service Exception: " + exception);


        return response;
    }


}
