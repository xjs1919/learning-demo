package com.github.xjs.kafka.config;

import com.github.xjs.kafka.filter.IdempotentFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {
    @Bean
    public IdempotentFilter idempotentFilter(){
        return new IdempotentFilter();
    }
}
