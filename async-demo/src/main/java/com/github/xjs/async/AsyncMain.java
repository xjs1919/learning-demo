package com.github.xjs.async;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.Future;

public class AsyncMain {
    public static void main(String[] args)throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AsyncApp.class);
        AsyncBean bean = ctx.getBean(AsyncBean.class);
        testSyncAsync(bean);
        //testAsyncResult(bean);
    }

    private static void testSyncAsync(AsyncBean bean){
        bean.printSync();
        bean.printAsync();
    }

    private static void testAsyncResult(AsyncBean bean)throws Exception{
        Future<String> future = bean.printAsyncFuture();
        System.out.println(future.get());
        String str = bean.printAsyncNoFuture();
        System.out.println(str);
    }
}
