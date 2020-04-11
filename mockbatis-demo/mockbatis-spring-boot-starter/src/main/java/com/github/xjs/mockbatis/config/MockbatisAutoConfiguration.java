package com.github.xjs.mockbatis.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class MockbatisAutoConfiguration {

    @Configuration
    @Import(MockScannerRegistrar.class)
    @ConditionalOnMissingBean(MockMapperFactoryBean.class)
    public static class MockScanAutoConfiguration{

    }
}
