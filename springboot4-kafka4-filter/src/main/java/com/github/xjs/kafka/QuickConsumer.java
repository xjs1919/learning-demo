package com.github.xjs.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class QuickConsumer {

    private static Logger log = LoggerFactory.getLogger(QuickConsumer.class);

    @KafkaListener(topics = "quickstart-events", filter = "idempotentFilter")
    public void onMessage(String message){
        // 读取TraceId
        log.info("on message:{}", message);
    }

}
