package com.taylor.stocks.routes;

import com.taylor.stocks.error.ErrorResponse;
import com.taylor.stocks.error.StockException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ErrorHandler implements ErrorController {

    @ResponseBody
    @RequestMapping("error")
    public ErrorResponse handleRuntimeException(HttpServletRequest request) {

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        var exception = request.getAttribute("javax.servlet.error.exception");

        ErrorResponse response = new ErrorResponse();
        response.setMessage("Exception: " + exception);
        response.setStatus(statusCode);

        return response;
    }


}
