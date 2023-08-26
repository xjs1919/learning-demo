package com.github.xjs.feign;

import com.github.xjs.feign.fallback.GoodsFallbackFactory;
import org.springframework.context.annotation.Bean;

public class FeignConfig {
    @Bean
    public GoodsFallbackFactory goodsFallbackFactory(){
        return new GoodsFallbackFactory();
    }
}
