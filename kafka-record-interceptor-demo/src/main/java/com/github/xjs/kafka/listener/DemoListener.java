package com.github.xjs.kafka.listener;

import com.github.xjs.kafka.utils.RequestContext;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Iterator;


@Component
public class DemoListener {

    private static Logger log = LoggerFactory.getLogger(DemoListener.class);

    @KafkaListener(topics = {"test-topic"}, filter = "traceIdFilter")
    public void onMessage(ConsumerRecord record){
        Object value = record.value();
        log.info("收到ConsumerRecord消息：{}, traceId:{}", value, RequestContext.getTraceId());
    }

}
