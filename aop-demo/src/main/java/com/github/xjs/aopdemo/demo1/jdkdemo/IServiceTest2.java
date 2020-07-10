package com.github.xjs.aopdemo.demo1.jdkdemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class IServiceTest2 {

    public static void main(String[] args) throws Exception {
        // 创建代理类的处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是InvocationHandler，被调用的方法是：" + method.getName());
                System.out.println(proxy.getClass().getName());
                return null;
            }
        };
        // 创建代理实例
        IService proxyService = (IService)Proxy.newProxyInstance(IService.class.getClassLoader(),new Class[]{IService.class}, invocationHandler);
        // 调用代理的方法
        proxyService.m1();
    }
}

