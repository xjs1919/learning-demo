package com.github.xjs.feign;

import com.github.xjs.feign.fallback.GoodsFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "goods-service",
        fallbackFactory = GoodsFallbackFactory.class,
        configuration = FeignConfig.class)
public interface GoodsClient {
    @GetMapping("/goods/{id}")
    public String queryById(@PathVariable("id") Long id);
}
