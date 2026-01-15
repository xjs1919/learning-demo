package com.github.xjs.kafka.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class IdempotentFilter implements RecordFilterStrategy<String, String> {
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