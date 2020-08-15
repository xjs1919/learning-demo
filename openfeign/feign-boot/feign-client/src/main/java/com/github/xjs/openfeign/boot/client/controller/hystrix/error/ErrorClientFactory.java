package com.github.xjs.openfeign.boot.client.controller.hystrix.error;

import com.netflix.hystrix.HystrixCommand;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Service;

@Service
public class ErrorClientFactory implements FallbackFactory<ErrorClient> {

    @Override
    public ErrorClient create(Throwable throwable) {
        return new ErrorClient(){
            @Override
            public String hello() {
                return "ErrorClientFactory Fallback";
            }
        };
    }
}
