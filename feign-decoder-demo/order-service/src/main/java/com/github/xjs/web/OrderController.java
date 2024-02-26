package com.github.xjs.web;

import com.github.xjs.feign.UserClient;
import com.github.xjs.feign.UserContext;
import com.github.xjs.pojo.Order;
import com.github.xjs.pojo.R;
import com.github.xjs.pojo.User;
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
    public R<Order> queryById(@PathVariable("id") Long id) {
        //设置threadlocal
        UserContext.setUserId(100L);
        R<User> userR = userClient.queryById(id);
        Order order = new Order(id, "测试商品", "100", id, userR.getData());
        return R.ok(order);
    }

}
