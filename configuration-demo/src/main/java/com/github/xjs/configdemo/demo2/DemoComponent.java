package com.github.xjs.configdemo.demo2;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//添加@Component类
@Component
public class DemoComponent {
    //这里面同样定义了2个类，Service3和Service4
    @Bean
    public Service3 service3(){
        return new Service3();
    }
    @Bean
    public Service4 service4(){
        return new Service4(service3());
    }

    //注意这里的方法修饰符：private static final
    @Bean
    private static final Service5 service5(){
        return new Service5();
    }
}