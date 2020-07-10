package com.github.xjs.aopdemo.demo2.test2;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.framework.AopProxyFactory;
import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

public class Test3 {
    public static void main(String[] args) {
        //1.创建代理所需参数配置（如：采用什么方式的代理、通知列表等）
        AdvisedSupport advisedSupport = new AdvisedSupport();
        //如：添加一个前置通知
        advisedSupport.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, @Nullable Object target) throws Throwable {
                String userName = (String) args[0];
                //如果不是路人的时候，抛出非法访问异常
                if (!"路人".equals(userName)) {
                    throw new RuntimeException(String.format("[%s]非法访问!", userName));
                }
            }
        });
        //设置被代理的目标对象
        advisedSupport.setTarget(new FundsService());
        //2.根据配置信息获取AopProxy对象，AopProxy用来负责创建最终的代理对象
        // AopProxy接口有2个实现类（JDK动态代理、cglib代理）
        // 具体最终会使用哪种方式，需要根据AdvisedSupport中指定的参数来判断
        // 创建AopProxy使用了简单工厂模式
        AopProxyFactory aopProxyFactory = new DefaultAopProxyFactory();
        //通过AopProxy工厂获取AopProxy对象
        AopProxy aopProxy = aopProxyFactory.createAopProxy(advisedSupport);
        //3.通过AopProxy创建代理对象
        Object proxy = aopProxy.getProxy();
    }
}
