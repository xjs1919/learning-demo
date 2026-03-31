package com.github.xjs.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class QuickConsumer {

    private static Logger log = LoggerFactory.getLogger(QuickConsumer.class);


    @KafkaListener(topics = "retry-topic")
    public void onMessage(String message){
        log.info("收到消息：{}", message);
    }
}
