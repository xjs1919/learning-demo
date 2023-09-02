package com.github.xjs.web;

import com.github.xjs.feign.UserClient;
import com.github.xjs.pojo.Order;
import com.github.xjs.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public Order queryById(@PathVariable("id") Long id) {
        Order order = new Order(id, "测试商品", "100", id, userClient.queryById(id));
        return order;
    }

    @GetMapping("/async/{id}")
    public Order queryByIdAsync(@PathVariable("id") Long id) {
        log.info("--->queryByIdAsync()");
        Order order = new Order(id, "测试商品", "100", id, orderService.queryById(id));
        return order;
    }

}
