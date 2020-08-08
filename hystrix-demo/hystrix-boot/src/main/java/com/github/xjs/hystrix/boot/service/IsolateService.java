package com.github.xjs.hystrix.boot.service;

import com.github.xjs.hystrix.boot.controller.IsolationController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class IsolateService {

    private static Logger log = LoggerFactory.getLogger(IsolateService.class);

    @HystrixCommand(
        commandProperties = {
                @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
        }
    )
    public String hello(){
        log.info("IsolateService user:" + IsolationController.UserHolder.getUser());
        return "IsolateService user:" + IsolationController.UserHolder.getUser();
    }

    @HystrixCommand
    public String world(){
        log.info("IsolateService user:" + IsolationController.UserHolder.getUser());
        return "IsolateService user:" + IsolationController.UserHolder.getUser();
    }
}
