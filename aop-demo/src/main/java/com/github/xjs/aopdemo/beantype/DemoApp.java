package com.github.xjs.aopdemo.beantype;

import com.github.xjs.aopdemo.service.IService;
import com.github.xjs.aopdemo.service.Service1;
import com.github.xjs.aopdemo.service.Service2;
import com.github.xjs.aopdemo.springdemo.DemoAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:com/github/xjs/aopdemo/beantype/beans.xml")
public class DemoApp {
    @Bean
    public DemoAdvice demoAdvice(){
        return new DemoAdvice();
    }
    @Bean
    public IService iService(){
        return new Service1();
    }
    @Bean
    public Service2 service2(){
        return new Service2();
    }
}