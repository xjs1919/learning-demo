package com.github.xjs.openfeign.boot.client.controller.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Service;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/8/15 11:23
 */
@Service
public class ServerHystrixFeignClientFallbackFactory implements FallbackFactory<ServerHystrixFeignClient> {
    @Override
    public ServerHystrixFeignClient create(Throwable cause) {
        //feign.FeignException$InternalServerError
        System.out.println(cause.getClass());
        return new ServerHystrixFeignClient(){
            @Override
            public String hello() {
                return "ServerHystrixFeignClientFallbackFactory fallback";
            }
            @Override
            public HystrixCommand<String> hystrixCommand(){
                return new HystrixCommand<String>(HystrixCommandGroupKey.Factory.asKey("HystrixCommandGroupKey")) {
                    @Override
                    protected String run() throws Exception {
                        return "ServerHystrixFeignClientFallbackFactory HystrixCommand";
                    }
                };
            }
        };
    }
}
