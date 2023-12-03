package com.github.xjs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试类")
@RestController
public class DemoController {

    @ApiOperation("测试方法")
    @GetMapping("/demo")
    public String demo(){
        return "demo";
    }
}
