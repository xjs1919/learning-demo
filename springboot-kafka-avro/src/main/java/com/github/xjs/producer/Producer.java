package com.github.xjs.producer;

import com.github.xjs.protocol.UserRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class Producer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(UserRecord record) {
        if (Objects.isNull(record)) {
            return;
        }
        log.info("send message, value:{}", record.toString());
        kafkaTemplate.send("demo-topic", record);
    }
}