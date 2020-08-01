package com.github.xjs.hystrix.demo1;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;
import rx.Observer;

import java.util.concurrent.Future;

public class CommandHelloWorld extends HystrixCommand<String> {
    private final String name;
    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }
    @Override
    protected String run() {
        // a real example would do work like a network call here
        return "Hello " + name + "!";
    }
    public static void main(String[] args) throws Exception{
        System.out.println("------------------同步执行------------------");
        CommandHelloWorld command = new CommandHelloWorld("Joshua");
        System.out.println(command.execute());
        System.out.println("------------------异步执行------------------");
        command = new CommandHelloWorld("Joshua");
        Future<String> future = command.queue();
        System.out.println(future.get());
        System.out.println("------------------响应式 阻塞执行------------------");
        command = new CommandHelloWorld("Joshua");
        System.out.println(command.observe().toBlocking().single());
        System.out.println("------------------响应式 非阻塞执行------------------");
        command = new CommandHelloWorld("Joshua");
        command.observe().subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                // nothing needed here
                System.out.println("onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
            @Override
            public void onNext(String v) {
                System.out.println("onNext: " + v);
            }
        });
    }
}