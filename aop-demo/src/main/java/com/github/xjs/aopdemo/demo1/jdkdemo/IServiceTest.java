package com.github.xjs.aopdemo.demo1.jdkdemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class IServiceTest {

    public static void main(String[] args) throws Exception {
        // 1. 获取接口对应的代理类
        Class<?>  proxyClass = Proxy.getProxyClass(IService.class.getClassLoader(), IService.class);
        // 2. 创建代理类的处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是InvocationHandler，被调用的方法是：" + method.getName());
                System.out.println(proxy.getClass().getName());
                return null;
            }
        };
        // 3. 创建代理实例
        IService proxyService = (IService) proxyClass.getConstructor(InvocationHandler.class).newInstance(invocationHandler);
        // 4. 调用代理的方法
        proxyService.m1();
    }
}

