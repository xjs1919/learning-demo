package com.github.xjs.aopdemo.demo1.cglibdemo;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.LazyLoader;

public class LazyDemo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service1.class);
        //创建一个LazyLoader对象
        LazyLoader lazyLoader = new LazyLoader() {
            @Override
            public Object loadObject() throws Exception {
                System.out.println("调用LazyLoader.loadObject()方法");
                return new Service1();
            }
        };
        enhancer.setCallback(lazyLoader);
        Object proxy = enhancer.create();
        Service1 service1 = (Service1) proxy;
        System.out.println("第1次调用");
        service1.m1();
        System.out.println("第2次调用");
        service1.m1();
    }
}
