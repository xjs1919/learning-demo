package com.github.xjs.goods.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/goods/")
public class GoodsController {

    @GetMapping("/{goodsId}")
    Goods getById(@PathVariable("goodsId")Integer goodsId){
        return new Goods(goodsId);
    }

    record Goods(Integer id){}
}
