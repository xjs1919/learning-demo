package com.github.xjs.openfeign.boot.client.controller.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.stereotype.Service;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/8/15 11:38
 */
@Service
public class ServerHystrixFeignClientFallback implements ServerHystrixFeignClient{

    @Override
    public String hello() {
        return "ServerHystrixFeignClientFallback fallback";
    }

    @Override
    public HystrixCommand<String> hystrixCommand(){
        return new HystrixCommand<String>(HystrixCommandGroupKey.Factory.asKey("HystrixCommandGroupKey")) {
            @Override
            protected String run() throws Exception {
                return "ServerHystrixFeignClientFallback HystrixCommand";
            }
        };
    }
}
