package com.github.xjs.config;

import com.github.xjs.feign.TraceIdInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public TraceIdInterceptor requestIdInterceptor(){
        return new TraceIdInterceptor();
    }

}
