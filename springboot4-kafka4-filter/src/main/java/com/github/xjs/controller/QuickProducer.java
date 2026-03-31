package com.github.xjs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
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
    public void send(@PathVariable("msg")String msg){
        // 手动设置msg_id，防止IdempotentInterceptor重复设置
        GenericMessage<String> message = new GenericMessage<>(msg, Map.of(
                "MSG_ID", UUID.randomUUID().toString(),
                KafkaHeaders.TOPIC,  "quickstart-events"));
        // 发送两条msg_id一样的消息
        kafkaTemplate.send(message);
        log.info("send message:{}", msg);
        kafkaTemplate.send(message);
        log.info("send message:{}", msg);
    }

}
