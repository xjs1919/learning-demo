package com.github.xjs.openfeign.boot.client.controller.hystrix.isolate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/hystrix/isolate")
public class ClientIsolateController {

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
    private ServerIsolateClient client;

    @GetMapping("/hello")
    public String hello(){
        UserHolder.setUser("Joshua");
        return client.hello();
    }

}
