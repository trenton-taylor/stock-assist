package com.taylor.stocks.routes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Heartbeat {

    @RequestMapping("heartbeat")
    public String heartbeat(){
        return "ok";
    }

}
