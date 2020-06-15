package com.github.xjs.mvcdemo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan( basePackages={"com.github.xjs.mvcdemo"},
        excludeFilters = { @ComponentScan.Filter(type= FilterType.ANNOTATION,value= EnableWebMvc.class)}
)
public class SpringContextConfig {

}