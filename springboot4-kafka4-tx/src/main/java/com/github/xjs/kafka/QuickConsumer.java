package com.github.xjs.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class QuickConsumer {

    private static Logger log = LoggerFactory.getLogger(QuickConsumer.class);

    @KafkaListener(topics = "topic1")
    public void onTopic1Message(String message){
        log.info("收到topic1的消息：{}", message);
    }

    @KafkaListener(topics = "topic2")
    public void onTopic2Message(String message){
        log.info("收到topic2的消息：{}", message);
    }

}
