package com.github.xjs.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

@Service
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass() == UserService.class){
            System.out.println("BeanPostProcessor.postProcessBeforeInitialization()");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass() == UserService.class){
            System.out.println("BeanPostProcessor.postProcessAfterInitialization()");
        }
        return bean;
    }
}