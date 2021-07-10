package com.taylor.stocks.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.servlet.error.ErrorController;

import javax.servlet.http.HttpServletRequest;

/**
 * Replace the white label error page with a custom response body.
 */
@ExtendWith(MockitoExtension.class)
public class ErrorHandlerTest implements ErrorController {

    @Mock
    HttpServletRequest request;

    @InjectMocks
    ErrorHandler errorHandler;

    @Test
    public void test404(){
        Mockito.when(request.getAttribute(Mockito.anyString())).thenReturn(404);
        Assertions.assertEquals(errorHandler.handleRuntimeException(request).getMessage(), "Service Exception: Route not found.");
    }

    @Test
    public void test500(){
        Mockito.when(request.getAttribute(Mockito.anyString())).thenReturn(500);
        Assertions.assertEquals(errorHandler.handleRuntimeException(request).getMessage(), "Service Exception: Internal Server Error.");
    }

    @Test
    public void testException(){
        Mockito.when(request.getAttribute(Mockito.anyString())).thenReturn(400, "Error");
        Assertions.assertEquals(errorHandler.handleRuntimeException(request).getMessage(), "Service Exception: Error");
    }


}
