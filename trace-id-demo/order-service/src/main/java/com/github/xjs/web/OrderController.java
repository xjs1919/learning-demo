package com.github.xjs.web;

import com.github.xjs.feign.UserClient;
import com.github.xjs.pojo.Order;
import com.github.xjs.pojo.User;
import com.github.xjs.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public Order queryById(@PathVariable("id") Long id) {
        Order order = new Order(id, "测试商品", "100", id, userClient.queryById(id));
        return order;
    }

    @GetMapping("/rest/{id}")
    public Order queryByIdUseRestTemplate(@PathVariable("id") Long id) {
        User user = restTemplate.getForObject("http://localhost:8081/user/" + id, User.class);
        Order order = new Order(id, "测试商品", "100", id, user);
        return order;
    }

    @GetMapping("/async/{id}")
    public Order queryByIdAsync(@PathVariable("id") Long id) {
        log.info("--->queryByIdAsync()");
        Order order = new Order(id, "测试商品", "100", id, orderService.queryById(id));
        return order;
    }

}
