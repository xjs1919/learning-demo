package com.github.xjs.iocdemo.inject1;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan
public class DemoApp {

    @Bean
    @Qualifier("s1")
    public IService service1(){
        return new Service1();
    }

    @Bean
    @Qualifier("s1")
    @Primary
    public IService service2(){
        return new Service2();
    }

    @Bean
    public Service3 service3(@Qualifier("s1") IService iService){
        return new Service3(iService);
    }

}
