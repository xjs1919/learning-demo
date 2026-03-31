package com.github.xjs.controller;

import com.github.xjs.kafka.QuickConsumer;
import com.github.xjs.pojo.Farewell;
import com.github.xjs.pojo.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/producer")
@RestController
public class QuickProducer {

    private static Logger log = LoggerFactory.getLogger(QuickConsumer.class);

    @Autowired
    private KafkaTemplate<String, String> stringKafkaTemplate;
    @Autowired
    private KafkaTemplate<String, Object> objectKafkaTemplate;

    @GetMapping("/send/{msg}")
    public void send(@PathVariable("msg")String msg){
        // 如果是key-value都是string类型，可以直接发送和接收
        stringKafkaTemplate.send("quickstart-events", msg);
        log.info("send message:{}", msg);
    }

    @GetMapping("/send/pojo")
    public void sendPojo(){
        Greeting greeting = new Greeting("this is a message", "xjs1919");
        objectKafkaTemplate.send("pojo-topic", greeting);
        log.info("send Greeting message:{}", greeting);
    }

    @GetMapping("/send/pojos")
    public void sendPojos(){
        Greeting greeting = new Greeting("this is a greeting message", "xjs1919");
        objectKafkaTemplate.send("pojo-topic", greeting);
        log.info("send greeting message:{}", greeting);

        Farewell farewell = new Farewell("this is a farewell message", 1);
        objectKafkaTemplate.send("pojo-topic", farewell);
        log.info("send farewell message:{}", greeting);

    }
}
