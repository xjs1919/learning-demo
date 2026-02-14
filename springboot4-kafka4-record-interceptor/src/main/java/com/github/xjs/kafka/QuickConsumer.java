package com.github.xjs.kafka;

import com.github.xjs.utils.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class QuickConsumer {

    private static Logger log = LoggerFactory.getLogger(QuickConsumer.class);

    @KafkaListener(topics = "quickstart-events")
    public void onMessage(String message){
        // 读取TraceId
        log.info("on message:{}, traceId:{}", message, RequestContext.getTraceId());
    }

}
