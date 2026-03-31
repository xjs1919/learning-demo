package com.github.xjs.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;

public class IdempotentInterceptor implements ProducerInterceptor<String, String> {

    private static Logger log = LoggerFactory.getLogger(IdempotentInterceptor.class);

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        Headers headers = record.headers();
        Header header = headers.lastHeader("MSG_ID");
        if(header == null){
            record.headers().add("MSG_ID", UUID.randomUUID().toString().getBytes());
        }
        log.info("发送消息:{}，msg id:{}", record.value(), new String(record.headers().lastHeader("MSG_ID").value()));
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if(e == null){
            log.info("发送消息成功");
        } else {
            log.error("发送消息失败 : {}", e);
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