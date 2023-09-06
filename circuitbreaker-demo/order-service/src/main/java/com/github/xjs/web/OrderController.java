package com.github.xjs.web;

import com.github.xjs.feign.UserClient;
import com.github.xjs.pojo.Order;
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

    @GetMapping("/{id}")
    public Order queryById(@PathVariable("id") Long id) {
        log.info("OrderController#queryById id:{}", id);
        Order order = new Order(id, "测试商品", "100", id, userClient.queryById(id));
        return order;
    }

}
