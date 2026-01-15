package com.github.xjs.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
@RestController
public class DemoController {

    private static Logger log = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("send/{msg}")
    public String send(@PathVariable("msg")String msg){
        CompletableFuture future = kafkaTemplate.send("test-topic", msg);
        try{
            future.get();
            log.info("消息发送成功");
        }catch(Exception e){
            e.printStackTrace();
        }
        return "OK";
    }
}
