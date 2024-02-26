package com.github.xjs.feign;

import org.springframework.context.annotation.Bean;

public class FeignConfig {

    @Bean
    public FeignDecode feignDecode(){
        return new FeignDecode();
    }
}
