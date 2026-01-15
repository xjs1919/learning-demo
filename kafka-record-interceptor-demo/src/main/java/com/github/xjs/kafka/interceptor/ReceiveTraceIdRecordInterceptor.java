package com.github.xjs.kafka.interceptor;

import com.github.xjs.kafka.utils.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.header.Header;
import org.springframework.kafka.listener.RecordInterceptor;


@Slf4j
public class ReceiveTraceIdRecordInterceptor implements RecordInterceptor<Object, Object> {

    @Override
    public ConsumerRecord<Object, Object> intercept(ConsumerRecord<Object, Object> record, Consumer<Object, Object> consumer) {
        Header header = record.headers().lastHeader(RequestContext.TRACE_ID);
        if(header != null){
            log.info("ReceiveTraceIdRecordInterceptor 设置 TraceId");
            RequestContext.setTraceId(new String(header.value()));
        }
        return record;
    }
}
