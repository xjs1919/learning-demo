package com.github.xjs.aopdemo.demo1.cglibdemo;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.FixedValue;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Test3 {
    public static void main(String[] args)throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service3.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "路人甲";
            }
        });
        Service3 proxy = (Service3) enhancer.create();
        System.out.println(proxy.m1());//@1
        System.out.println(proxy.m2()); //@2
        System.out.println(proxy.toString());//@3
    }
}
