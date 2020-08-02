package com.github.xjs.openfeign.demo7hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import feign.RequestLine;
import feign.Target;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;

import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;

public class Client2 {


    public static class MySetterFactory implements SetterFactory{
        @Override
        public HystrixCommand.Setter create(Target<?> target, Method method) {
            String groupKey = target.name();
            String commandKey = method.getName()+"_"+method.getAnnotation(RequestLine.class).value();
            System.out.println(groupKey+","+commandKey);
            return HystrixCommand.Setter
                    .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                    .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey));
        }
    }
    public static void main(String[] args)throws Exception {
        Demo demo = HystrixFeign.builder()
                .setterFactory(new MySetterFactory())
                .target(Demo.class,
                        "http://localhost:8080/");
        String result = demo.sync(100, "Joshua");
        System.out.println(result);
    }
}
