package com.github.xjs.hystrix.demo4cache;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

public class CommandUsingRequestCache extends HystrixCommand<Boolean> {
    private final int value;
    protected CommandUsingRequestCache(int value) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.value = value;
    }
    @Override
    protected Boolean run() {
        System.out.println("-----------execute run()---------------");
        return value == 0 || value % 2 == 0;
    }
    //1.定义缓存的key
    @Override
    protected String getCacheKey() {
        return String.valueOf(value);
    }
    public static void main(String[] args) {
        //2.初始化缓存的context
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            //执行同样的command就会走缓存了
            CommandUsingRequestCache command2a = new CommandUsingRequestCache(2);
            CommandUsingRequestCache command2b = new CommandUsingRequestCache(2);
            //第一次，缓存不会命中
            System.out.println(command2a.execute());
            //输出false
            System.out.println(command2a.isResponseFromCache());
            //第二次，缓存命中
            System.out.println(command2b.execute());
            //输出true
            System.out.println(command2b.isResponseFromCache());
        } finally {
            context.shutdown();
        }
        // 开启了一个新的context，缓存被重置
        context = HystrixRequestContext.initializeContext();
        try {
            CommandUsingRequestCache command3b = new CommandUsingRequestCache(2);
            System.out.println(command3b.execute());
            //输出false
            System.out.println(command3b.isResponseFromCache());
        } finally {
            context.shutdown();
        }
    }
}