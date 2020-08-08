package com.github.xjs.hystrix.boot.controller;

import com.github.xjs.hystrix.boot.service.IsolateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/isolate")
public class IsolationController {

    private static Logger log = LoggerFactory.getLogger(IsolationController.class);

    public static class UserHolder{
        private static ThreadLocal<String> userHolder = new ThreadLocal<>();
        public static void setUser(String user){
            userHolder.set(user);
        }
        public static String getUser(){
            return userHolder.get();
        }
        public static void clean(){
            userHolder.remove();
        }
    }
    @Autowired
    private IsolateService isolateService;

    @GetMapping("/hello")
    public String hello(String msg){
        UserHolder.setUser("Joshua");
        log.info("IsolationController user:" + UserHolder.getUser());
        return isolateService.hello();
    }

    @GetMapping("/world")
    public String world(String msg){
        UserHolder.setUser("Joshua");
        log.info("IsolationController user:" + UserHolder.getUser());
        return isolateService.world();
    }
}
