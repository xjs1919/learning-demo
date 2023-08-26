package com.github.xjs.feign.fallback;

import com.github.xjs.feign.GoodsClient;
import org.springframework.cloud.openfeign.FallbackFactory;


public class GoodsFallbackFactory implements FallbackFactory<GoodsClient> {
    @Override
    public GoodsClient create(Throwable cause) {

        return new GoodsClient(){
            @Override
            public String queryById(Long id) {
                return "GoodsFallbackFactory:"+id;
            }
        };
    }
}
