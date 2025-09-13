package com.github.xjs.kafka.interceptor;

import com.github.xjs.kafka.utils.RequestContext;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SendTraceIdInterceptor implements ProducerInterceptor<String, String> {

    private static Logger log = LoggerFactory.getLogger(SendTraceIdInterceptor.class);

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        producerRecord.headers().add(RequestContext.TRACE_ID, RequestContext.getTraceId().getBytes());
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if(e == null){
            log.info("send successfully");
        } else {
            log.error("send error : {}", e);
        }
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> map) {
        log.info("configure:{}", map);
    }
}
