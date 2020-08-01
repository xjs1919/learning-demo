package com.github.xjs.hystrix.demo3fallback;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandHelloFailure extends HystrixCommand<String> {
    private final String name;
    public CommandHelloFailure(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }
    @Override
    protected String run() {
        throw new RuntimeException("this command always fails");
    }
    @Override
    protected String getFallback() {
        return "Hello Failure " + name + "!";
    }
    public static void main(String[] args) {
        System.out.println("------------fallback--------------");
        CommandHelloFailure command = new CommandHelloFailure("TEST");
        //这里会输出Hello Failure TEST!，并不会抛出异常
        System.out.println(command.execute());
    }
}