package com.github.xjs.controller;

import com.github.xjs.kafka.QuickConsumer;
import com.github.xjs.utils.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/producer")
@RestController
public class QuickProducer {

    private static Logger log = LoggerFactory.getLogger(QuickConsumer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/send/{msg}")
    public void send(@PathVariable("msg")String msg){
        // 设置traceId到ThreadLocal
        RequestContext.setTraceId(UUID.randomUUID().toString());
        kafkaTemplate.send("quickstart-events", msg);
        log.info("send message:{}, traceId:{}", msg, RequestContext.getTraceId());
    }

}
