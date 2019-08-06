package com.github.xjs.ajaxserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
//@CrossOrigin
public class TestController {

        @GetMapping("/get1")
        public ResultBean get1(){
            log.info("TestController:get1");
            return new ResultBean("get1 ok");
        }

    @GetMapping(value="/jsonp", produces = "application/javascript")
    public String jsonp(@RequestParam("callback")String callback){
        log.info("TestController:jsonp");
        return callback + "('jsonp ok')";
    }

    @PostMapping(value="/postJson")
    public ResultBean postJson(@RequestBody User user){
        log.info("TestController:postJson");
        return  new ResultBean(user);
    }

    @GetMapping("/getCookie")
    public ResultBean getCookie(@CookieValue(name="cookie1")String cookieValue){
        log.info("TestController:getCookie");
        return  new ResultBean(cookieValue);
    }

    @GetMapping("/getHeader")
    public ResultBean getHeader(@RequestHeader("x-header1")String header1){
        log.info("TestController:getHeader");
        return  new ResultBean(header1);
    }
    private static Logger log = LoggerFactory.getLogger(TestController.class);

}
