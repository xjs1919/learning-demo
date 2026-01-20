package com.github.xjs.client.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(url = "/api/order")
public interface OrderApi {

    @GetExchange(url="/getParam")
    Order getParam(@RequestParam("orderId")Integer orderId);

    @GetExchange(url="/getPath/{orderId}")
    Order getPath(@PathVariable("orderId")Integer orderId);

    @PostExchange("/post")
    Order post(@RequestBody Order order);

    record Order(Integer orderId, String name){}

}
