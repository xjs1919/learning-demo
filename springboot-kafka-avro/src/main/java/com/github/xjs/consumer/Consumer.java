package com.github.xjs.consumer;

import com.github.xjs.protocol.UserRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {
   
    @KafkaListener(topics = "demo-topic")
    public void consume(ConsumerRecord<String, UserRecord> user){
        log.info("receive message, topic:{}, key:{}, value:{}", user.topic(), user.key(), user.value());
    }
}