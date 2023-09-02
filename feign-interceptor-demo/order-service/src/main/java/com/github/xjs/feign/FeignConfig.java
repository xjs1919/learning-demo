package com.github.xjs.feign;

import org.springframework.context.annotation.Bean;

public class FeignConfig {
    @Bean
    public FeignRelayUserInterceptor feignRelayUserInterceptor(){
        return new FeignRelayUserInterceptor();
    }
}
