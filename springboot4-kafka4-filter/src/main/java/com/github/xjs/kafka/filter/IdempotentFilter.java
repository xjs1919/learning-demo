package com.github.xjs.kafka.filter;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

import java.util.HashMap;
import java.util.Map;

public class IdempotentFilter implements RecordFilterStrategy<String, String> {
    private static Logger log = LoggerFactory.getLogger(IdempotentFilter.class);
    private Map<String, String> redis = new HashMap<>();
    @Override
    public boolean filter(ConsumerRecord<String, String> consumerRecord) {
        Headers headers = consumerRecord.headers();
        Header header = headers.lastHeader("MSG_ID");
        if(header == null){
            return false;
        }
        String msgId = new String(header.value());
        if(redis.containsKey(msgId)){
            log.info("丢弃重复消息:{} msg id:{}", consumerRecord.value(), msgId);
            return true;
        }else{
            redis.put(msgId, msgId);// set cache timeout
            return false;
        }
    }
}