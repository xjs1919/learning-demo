package com.github.xjs.iocdemo.scopedemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configuration
public class DemoApp implements BeanFactoryAware {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        ((ConfigurableBeanFactory)beanFactory).registerScope(ThreadLocalScope.class.getSimpleName(), threadLocalScope());
    }

    @Bean
    public ThreadLocalScope threadLocalScope(){
        return new ThreadLocalScope();
    }

    @Bean
    @Scope("ThreadLocalScope")
    public Service1 service1(){
       return new Service1();
    }
}
