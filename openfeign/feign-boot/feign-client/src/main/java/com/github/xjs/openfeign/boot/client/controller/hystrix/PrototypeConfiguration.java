package com.github.xjs.openfeign.boot.client.controller.hystrix;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/8/15 13:09
 */
public class PrototypeConfiguration {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }
}
