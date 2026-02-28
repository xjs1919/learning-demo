package com.github.xjs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RequestMapping("/api/producer")
@RestController
public class QuickProducer {

    private static Logger log = LoggerFactory.getLogger(QuickProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/send/{msg}")
    @Transactional(rollbackFor = Exception.class)
    public void send(@PathVariable("msg")String msg) throws Exception{
        log.info("向topic1发送消息");
        kafkaTemplate.send("topic1", msg);
        Thread.sleep(5000);
        log.info("向topic2发送消息");
        kafkaTemplate.send("topic2", msg);
    }

    @GetMapping("/send2/{msg}")
    public Boolean send2(@PathVariable("msg")String msg){
        boolean result = kafkaTemplate.executeInTransaction(kt->{
            kt.send("topic1", msg);
            kt.send("topic2", msg);
            return true;
        });
        return true;
    }

    @GetMapping("/send3/{msg}")
    @Transactional(rollbackFor = Exception.class)
    public void send3(@PathVariable("msg")String msg)throws Exception{
        log.info("向topic1发送消息");
        kafkaTemplate.send("topic1", msg);
        Thread.sleep(5000);
        int a = 1/0;
        log.info("向topic2发送消息");
        kafkaTemplate.send("topic2", msg);
    }

    @GetMapping("/register")
    public void register(@PathVariable("msg")String msg)throws Exception{
        log.info("向topic1发送消息");
        kafkaTemplate.send("topic1", msg);
        Thread.sleep(5000);
        int a = 1/0;
        log.info("向topic2发送消息");
        kafkaTemplate.send("topic2", msg);
    }






}
