package com.github.xjs.controller;

import com.github.xjs.util.ExceptionUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequestMapping("/api/producer")
@RestController
public class QuickProducer {

    private static Logger log = LoggerFactory.getLogger(QuickProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/send/{msg}")
    public void send(@PathVariable("msg")String msg) throws Exception {
        log.info("发送消息：{}", msg);
        kafkaTemplate.send("retry-topic", msg);
    }
}
