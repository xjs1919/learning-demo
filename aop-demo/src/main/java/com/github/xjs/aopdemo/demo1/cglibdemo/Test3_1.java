package com.github.xjs.aopdemo.demo1.cglibdemo;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

public class Test3_1 {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service3.class);
        enhancer.setCallback(NoOp.INSTANCE);
        Service3 proxy = (Service3) enhancer.create();
        System.out.println(proxy.m1());
        System.out.println(proxy.m2());
    }
}
