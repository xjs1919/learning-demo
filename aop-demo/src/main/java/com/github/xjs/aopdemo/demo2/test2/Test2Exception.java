package com.github.xjs.aopdemo.demo2.test2;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Test2Exception {

    public static class ThrowAdvice implements ThrowsAdvice{
        //注意方法名称必须为afterThrowing
        public void afterThrowing(Method method, Object[] args, Object target, RuntimeException e) {
            //监控到异常后发送消息通知开发者
            System.out.println("异常警报：");
            System.out.println(String.format("method:[%s]，args:[%s]", method.toGenericString(), Arrays.stream(args).collect(Collectors.toList())));
            System.out.println(e.getMessage());
            System.out.println("请尽快修复bug！");
        }
    }

    public static void main(String[] args) {
        //代理工厂
        ProxyFactory proxyFactory = new ProxyFactory();
        //添加目标对象
        proxyFactory.setTarget(new FundsService());
        //添加一个异常通知，发现异常之后发送消息给开发者尽快修复bug
        proxyFactory.addAdvice(new ThrowAdvice());
        //通过代理工厂创建代理
        FundsService proxy = (FundsService) proxyFactory.getProxy();
        //调用代理的方法
        proxy.cashOut("路人", 200000);
    }
}
