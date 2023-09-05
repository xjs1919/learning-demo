package com.github.xjs.feign;

import com.github.xjs.feign.fallback.UserFallbackFactory;
import org.springframework.context.annotation.Bean;

public class FeignConfig {
    @Bean
    public UserFallbackFactory userFallbackFactory(){
        return new UserFallbackFactory();
    }
}
