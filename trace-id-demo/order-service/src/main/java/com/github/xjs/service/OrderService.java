package com.github.xjs.service;

import com.github.xjs.feign.UserClient;
import com.github.xjs.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private UserClient userClient;

    @Async(AsyncAnnotationBeanPostProcessor.DEFAULT_TASK_EXECUTOR_BEAN_NAME)
    public User queryById(Long userId){
        log.info("--->async queryById()");
        return userClient.queryById(userId);
    }
}
