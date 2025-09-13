package com.github.xjs.kafka.interceptor;

import com.github.xjs.kafka.utils.RequestContext;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class ReceiveTraceIdInterceptor implements ConsumerInterceptor<String, String> {

    private static Logger log = LoggerFactory.getLogger(ReceiveTraceIdInterceptor.class);

    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
        for(Iterator<ConsumerRecord<String, String>> recordIterator = records.iterator(); recordIterator.hasNext();){
            ConsumerRecord<String, String> record = recordIterator.next();
            Headers headers = record.headers();
            if(headers == null){
                continue;
            }
            for(Iterator<Header> headerIterator = headers.iterator(); headerIterator.hasNext();){
                Header header = headerIterator.next();
                if(Objects.equals(header.key(), RequestContext.TRACE_ID)){
                    RequestContext.setTraceId(new String(header.value()));
                }
            }
        }
        return records;
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> configs) {
        log.info("consumer configure:{}", configs);
    }
}
