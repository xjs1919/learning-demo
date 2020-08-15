package com.github.xjs.openfeign.boot.client.controller.hystrix.error;

import com.github.xjs.openfeign.boot.client.controller.hystrix.ServerHystrixFeignClientFallback;
import com.github.xjs.openfeign.boot.client.controller.hystrix.ServerHystrixFeignClientFallbackFactory;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@FeignClient(name="ErrorClient",
        url="http://localhost:8080",
        path = "/server/hystrix",
        fallbackFactory = ErrorClientFactory.class
)
public interface ErrorClient {

    @GetMapping("/error/hello")
    public String hello();
}
