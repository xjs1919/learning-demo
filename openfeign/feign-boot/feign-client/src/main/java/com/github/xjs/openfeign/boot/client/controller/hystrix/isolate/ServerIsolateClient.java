package com.github.xjs.openfeign.boot.client.controller.hystrix.isolate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="ServerIsolateClient",
        url="http://localhost:8080",
        path = "/server/hystrix",
        configuration = InterceptorConfigure.class
        )
public interface ServerIsolateClient {

        @GetMapping("/isolate/hello")
        public String hello();
}
