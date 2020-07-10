package com.github.xjs.aopdemo.demo2.test1;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;

public class Test1 {
    public static void main(String[] args) {
        //定义目标对象
        UserService target = new UserService();
        //创建pointcut，用来拦截UserService中的work方法
        Pointcut pointcut = new Pointcut(){
            @Override
            public ClassFilter getClassFilter() {
                return new ClassFilter(){
                    @Override
                    public boolean matches(Class<?> clazz) {
                        return clazz == UserService.class;
                    }
                };
            }
            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher(){
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        return method.getName().equals("work");
                    }
                    @Override
                    public boolean isRuntime() {
                        return false;
                    }
                    @Override
                    public boolean matches(Method method, Class<?> targetClass, Object... args) {
                        return false;
                    }
                };
            }
        };
        //创建通知，此处需要在方法之前执行操作，所以需要用到MethodBeforeAdvice类型的通知
        MethodBeforeAdvice advice = new MethodBeforeAdvice(){
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("before advice");
            }
        };
        //创建Advisor，将pointcut和advice组装起来
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        //通过spring提供的代理创建工厂来创建代理
        ProxyFactory proxyFactory = new ProxyFactory();
        //为工厂指定目标对象
        proxyFactory.setTarget(target);
        //调用addAdvisor方法，为目标添加增强的功能，即添加Advisor，可以为目标添加很多个Advisor
        proxyFactory.addAdvisor(advisor);
        //通过工厂提供的方法来生成代理对象
        UserService userServiceProxy = (UserService) proxyFactory.getProxy();
        userServiceProxy.work("xjs");
    }
}
