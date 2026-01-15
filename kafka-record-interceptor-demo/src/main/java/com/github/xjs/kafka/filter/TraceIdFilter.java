package com.github.xjs.kafka.filter;

import com.github.xjs.kafka.utils.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

@Slf4j
public class TraceIdFilter implements RecordFilterStrategy<Object, Object> {
    @Override
    public boolean filter(ConsumerRecord<Object, Object> consumerRecord) {
        Header header = consumerRecord.headers().lastHeader(RequestContext.TRACE_ID);
        if(header != null){
            log.info("TraceIdFilter 设置 TraceId");
            RequestContext.setTraceId(new String(header.value()));
        }
        return true;
    }
}
