package com.github.xjs.openfeign.boot.client.controller.hystrix;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="ServerHystrixPrototypeClient",
        url="http://localhost:8080",
        path = "/server/hystrix",
        configuration = PrototypeConfiguration.class,
        fallback = ServerHystrixPrototypeClientFallback.class
        )
public interface ServerHystrixPrototypeClient {
        @GetMapping("/prototype")
        public String prototype();
}
