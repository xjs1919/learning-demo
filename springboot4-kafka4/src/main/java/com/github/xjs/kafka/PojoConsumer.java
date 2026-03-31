package com.github.xjs.kafka;

import com.github.xjs.pojo.Farewell;
import com.github.xjs.pojo.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "pojo-topic")
public class PojoConsumer {

    private static Logger log = LoggerFactory.getLogger(QuickConsumer.class);

    @KafkaHandler
    public void handleGreeting(Greeting greeting) {
        log.info("Greeting received: {}",  greeting);
    }

    @KafkaHandler
    public void handleF(Farewell farewell) {
        log.info("Farewell received:{} ",  farewell);
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        log.info("Unkown type received:{} ", object);
    }
}
