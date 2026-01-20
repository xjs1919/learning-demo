package com.github.xjs.order.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/api/order/")
public class OrderController {

    @GetMapping("/getParam")
    public Order getParam(@RequestParam("orderId")Integer orderId){
        return new Order(orderId, "getParam");
    }

    @GetMapping("/getPath/{orderId}")
    public Order getPath(@PathVariable("orderId")Integer orderId){
        return new Order(orderId, "getPath");
    }

    @PostMapping("/post")
    public Order post(@RequestBody Order order){
        return new Order(new Random().nextInt(100), order.name);
    }

    public record Order(Integer orderId, String name){}

}
