package com.github.xjs.openfeign.boot.client.controller.hystrix.isolate;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Callable;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/8/15 13:35
 */
@Configuration
public class IsolateConfig {

    @Bean
    public HystrixConcurrencyStrategy hystrixConcurrencyStrategy(){
        return new ThreadLocalHystrixConcurrencyStrategy();
    }

    public static class ThreadLocalHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy{
        public ThreadLocalHystrixConcurrencyStrategy(){
            //注册成hystrix的插件
            HystrixPlugins.getInstance().registerConcurrencyStrategy(this);
        }
        public <T> Callable<T> wrapCallable(Callable<T> callable) {
            //先包装一下要执行的任务，在这里把ThreadLocal的值取出来
            return new ThreadLocalCallable<T>(callable);
        }
    }

    public static class ThreadLocalCallable<V> implements Callable<V>{
        private Callable<V> target;
        private String user;
        public  ThreadLocalCallable(Callable<V> target){
            this.target = target;
            //取ThreadLocal的值并保存起来
            this.user = ClientIsolateController.UserHolder.getUser();
        }
        @Override
        public V call() throws Exception {
            try{
                //真正执行的时候再设置进去
                ClientIsolateController.UserHolder.setUser(this.user);
                //真正执行的时候再设置进去
                return target.call();
            }finally {
                //执行完毕再清理掉
                ClientIsolateController.UserHolder.clean();
            }
        }
    }
}