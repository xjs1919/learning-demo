package com.github.xjs.client.controller;

import com.github.xjs.client.api.GoodsApi;
import com.github.xjs.client.api.OrderApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/client/")
public class ClientController {

    @Autowired
    private OrderApi orderApi;

    @Autowired
    private GoodsApi goodsApi;

    @GetMapping("/order/getParam")
    public String getParam(@RequestParam("orderId")Integer orderId){
        return "from server:" + orderApi.getParam(orderId);
    }

    @GetMapping("/order/getPath/{orderId}")
    public String getPath(@PathVariable("orderId")Integer orderId){
        return "from server:" + orderApi.getPath(orderId);
    }

    @GetMapping("/order/post/{name}")
    public String post(@PathVariable("name")String name){
        return "from server:" + orderApi.post(new OrderApi.Order(-1, name));
    }

    @GetMapping("/goods/{goodsId}")
    public GoodsApi.Goods getById(@PathVariable("goodsId")Integer goodsId){
        log.info("start getById:{}", goodsId);
        var goods = goodsApi.getById(goodsId);
        log.info("end getById");
        return goods;
    }

}
