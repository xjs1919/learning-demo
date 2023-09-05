package com.github.xjs.feign;

import com.github.xjs.feign.fallback.UserClientFallbackFactory;
import org.springframework.context.annotation.Bean;

public class FeignConfig {
    @Bean
    public UserClientFallbackFactory userClientFallbackFactory(){
        return new UserClientFallbackFactory();
    }
}
