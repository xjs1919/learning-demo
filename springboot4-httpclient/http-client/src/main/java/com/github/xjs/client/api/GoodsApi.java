package com.github.xjs.client.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url="/api/goods")
public interface GoodsApi {
    @GetExchange(url="/{goodsId}")
    Goods getById(@PathVariable("goodsId")Integer goodsId);

    record Goods(Integer id){}

}
