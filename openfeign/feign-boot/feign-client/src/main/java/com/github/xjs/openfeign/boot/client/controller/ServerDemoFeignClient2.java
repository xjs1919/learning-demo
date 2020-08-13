package com.github.xjs.openfeign.boot.client.controller;

import com.github.xjs.openfeign.boot.api.DemoApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="ServerDemoFeignClient2",
        url="http://localhost:8080")
public interface ServerDemoFeignClient2 extends DemoApi {
}
