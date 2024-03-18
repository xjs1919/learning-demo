package com.github.xjs.controller;

import com.github.xjs.websocket.WebsocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


    @Autowired
    private WebsocketServer websocketServer;


    @GetMapping("/send")
    public String demo(String clientId, String msg) throws Exception{
        websocketServer.sendToClient(clientId, msg);
        return "ok";
    }

}
