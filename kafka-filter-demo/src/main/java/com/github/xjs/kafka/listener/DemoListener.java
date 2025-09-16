package com.github.xjs.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class DemoListener {

    private static Logger log = LoggerFactory.getLogger(DemoListener.class);

    @KafkaListener(topics = {"test-topic"}, filter = "idempotentFilter")
    public void onMessage(ConsumerRecord record){
        Object value = record.value();
        log.info("收到消息：{}, msg id:{}", value,
                new String(record.headers().lastHeader("MSG_ID").value()));
    }
}
