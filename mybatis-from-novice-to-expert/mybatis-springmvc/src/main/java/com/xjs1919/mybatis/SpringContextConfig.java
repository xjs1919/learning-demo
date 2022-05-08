package com.xjs1919.mybatis;

/**
 * @author jiashuai.xujs
 * @date 2022/3/24 13:40
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages={"com.xjs1919.mybatis"},
        excludeFilters = {
            @ComponentScan.Filter(type= FilterType.ANNOTATION,value= EnableWebMvc.class),
            @ComponentScan.Filter(type= FilterType.ANNOTATION,value= Controller.class)
        }
)
public class SpringContextConfig {

}
