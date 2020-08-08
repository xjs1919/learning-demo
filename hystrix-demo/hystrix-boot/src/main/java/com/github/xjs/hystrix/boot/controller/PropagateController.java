package com.github.xjs.hystrix.boot.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/propagate")
public class PropagateController {

    public static class BizException extends HystrixBadRequestException{
        public BizException(String message) {
            super(message);
        }
        public BizException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class ParamException extends RuntimeException{
        public ParamException(String message) {
            super(message);
        }
    }

    @HystrixCommand(fallbackMethod = "helloFallback")
    @GetMapping("/hello")
    public String hello(){
        throw new BizException("参数校验异常");
    }

    @HystrixCommand(fallbackMethod = "helloFallback", ignoreExceptions = ParamException.class)
    @GetMapping("/world")
    public String world(){
        throw new ParamException("参数不能为空");
    }

    public String helloFallback(Throwable t) {
        return "hello fallback:" + t.getMessage();
    }
}
