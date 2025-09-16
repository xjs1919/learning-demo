package com.github.xjs.kafka.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
@RestController
public class DemoController {

    private static Logger log = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Msg{
        private String content;
    }

    @GetMapping("send/{msg}")
    public String send(@PathVariable("msg")String msg) {
        GenericMessage<String> message = new GenericMessage<>(msg, Map.of("MSG_ID", UUID.randomUUID().toString()));
        // 发送两条msg_id一样的消息
        kafkaTemplate.setDefaultTopic("test-topic");
        kafkaTemplate.send(message);
        kafkaTemplate.send(message);
        return "OK";
    }
}
