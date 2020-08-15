package com.github.xjs.openfeign.boot.client.controller.hystrix;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="ServerHystrixFeignClient",
        url="http://localhost:8080",
        path = "/server/hystrix",
        fallback = ServerHystrixFeignClientFallback.class,
        fallbackFactory = ServerHystrixFeignClientFallbackFactory.class
        )
public interface ServerHystrixFeignClient {
        @GetMapping("/hello")
        public String hello();

        @GetMapping("/hystrixCommand")
        public HystrixCommand<String> hystrixCommand();
}
