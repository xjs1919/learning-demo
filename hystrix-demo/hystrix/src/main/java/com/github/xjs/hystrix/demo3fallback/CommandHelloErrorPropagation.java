package com.github.xjs.hystrix.demo3fallback;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;

public class CommandHelloErrorPropagation extends HystrixCommand<String> {
    private final String name;
    public CommandHelloErrorPropagation(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }
    @Override
    protected String run() {
        throw new HystrixBadRequestException("参数校验异常，用户名不能为空", new BizException());
    }
    @Override
    protected String getFallback() {
        return "Hello Failure " + name + "!";
    }
    public static void main(String[] args) {
        System.out.println("------------fallback--------------");
        CommandHelloErrorPropagation command = new CommandHelloErrorPropagation("TEST");
        try{
            System.out.println(command.execute());
            //异常会直接传播出来
        }catch(HystrixBadRequestException e){
            //这里会拿到原始的异常信息
            if(e.getCause().getClass() == BizException.class){
                System.out.println(e.getMessage());
            }
        }
    }
    public static class BizException extends RuntimeException{
    }
}