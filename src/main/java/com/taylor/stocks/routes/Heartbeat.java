package com.taylor.stocks.routes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Heartbeat {

    /**
     * Returns a simple heartbeat to indicate the service is alive
     * @return
     */
    @RequestMapping("heartbeat")
    public String heartbeat(){
        return "ok";
    }

}
