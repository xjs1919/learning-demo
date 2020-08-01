package com.github.xjs.hystrix.demo6;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

public class CommandWithFallbackViaNetwork extends HystrixCommand<String> {
    private final int id;
    protected CommandWithFallbackViaNetwork(int id) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceX"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueCommand")));
        this.id = id;
    }
    @Override
    protected String run() {
        //模拟访问远程服务异常
        throw new RuntimeException("force failure for example");
    }
    @Override
    protected String getFallback() {
        //访问本地缓存
        return new FallbackViaNetwork(id).execute();
    }
    private static class FallbackViaNetwork extends HystrixCommand<String> {
        private final int id;
        public FallbackViaNetwork(int id) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceX"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueFallbackCommand"))
                    //使用不同的线程池
                    .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("RemoteServiceXFallback")));
            this.id = id;
        }
        @Override
        protected String run() {
            return "from cache";
        }
        @Override
        protected String getFallback() {
            //缓存也取不到，放弃
            return null;
        }
    }
}