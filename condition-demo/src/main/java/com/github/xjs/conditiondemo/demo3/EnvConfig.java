package com.github.xjs.conditiondemo.demo3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {

    @Bean
    @Env(profile = "dev")
    public String name(){
        return "开发环境";
    }

    @Bean
    @Env(profile = "product")
    public String username(){
        return "生产环境";
    }
}
