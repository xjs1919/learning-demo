package com.github.xjs.goods.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/goods/")
public class GoodsController {

    @GetMapping("/{goodsId}")
    Goods getById(@PathVariable("goodsId") Integer goodsId,
                  @RequestHeader(value = "token", required = false) String token,
                  @RequestHeader(value = "traceId", required = false) String traceId)throws Exception {
        log.info("token:{} traceId:{}", token, traceId);

        if (goodsId % 2 == 0) {
            throw new RuntimeException("Manually throws Exception");
        }

        if(goodsId % 3 == 0){
            Thread.sleep(10000);
        }

        return new Goods(goodsId);
    }

    record Goods(Integer id){}
}
