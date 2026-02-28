package com.github.xjs.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConsumer {

    private static Logger log = LoggerFactory.getLogger(EmployeeConsumer.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "employee")
    public void onEmployeeMessage(String message){
        log.info("收到employee的消息：{}", message);
    }

}
