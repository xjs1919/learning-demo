package com.github.xjs.kafka;

import com.github.xjs.pojo.Farewell;
import com.github.xjs.pojo.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class QuickConsumer {

    private static Logger log = LoggerFactory.getLogger(QuickConsumer.class);

    @KafkaListener(topics = "quickstart-events", containerFactory = "stringKafkaListenerContainerFactory")
    public void onMessage(String message){
        log.info("on message:{}", message);
    }

//    @KafkaListener(topics = "pojo-topic", containerFactory = "objectKafkaListenerContainerFactory")
//    public void onPojoMessage(Greeting greeting){
//        log.info("on pojo message:{}", greeting);
//    }


}
