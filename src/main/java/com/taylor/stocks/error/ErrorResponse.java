package com.taylor.stocks.error;

import lombok.Data;

@Data
public class ErrorResponse {

    public Integer status;
    public String message;

}
