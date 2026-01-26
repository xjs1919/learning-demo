package com.github.xjs.springboot4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@Import(EnvBeanRegistrar.class)
public class BeanRegistrarConfig {
}
