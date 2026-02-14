package com.github.xjs.kafka.filter;

import com.github.xjs.utils.RequestContext;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

public class ReceiveTraceIdFilter implements RecordFilterStrategy {
    private static Logger log = LoggerFactory.getLogger(ReceiveTraceIdFilter.class);
    @Override
    public boolean filter(ConsumerRecord consumerRecord) {
        Header header = consumerRecord.headers().lastHeader(RequestContext.TRACE_ID);
        if(header != null){
            String traceId = new String(header.value());
            log.info("RecordFilterStrategy设置TraceId：{}", traceId);
        }
        return false;
    }
}
