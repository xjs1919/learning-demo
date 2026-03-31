package com.github.xjs.kafka.interceptor;

import com.github.xjs.utils.RequestContext;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.RecordInterceptor;

public class ReceiveTraceIdRecordInterceptor implements RecordInterceptor<Object, Object> {

    private static Logger log = LoggerFactory.getLogger(ReceiveTraceIdRecordInterceptor.class);

    // 对消息进行前置处理。可以检查、修改或替换消息记录。
    // 如果此方法返回 null，则监听器不会被调用，相当于过滤掉了这条消息
    @Override
    public ConsumerRecord<Object, Object> intercept(ConsumerRecord<Object, Object> record, Consumer<Object, Object> consumer) {
        Header header = record.headers().lastHeader(RequestContext.TRACE_ID);
        if(header != null){
            RequestContext.setTraceId(new String(header.value()));
            log.info("RecordInterceptor设置TraceId:{}", RequestContext.getTraceId());
        }
        return record;
    }

    @Override
    public void success(ConsumerRecord<Object, Object> record, Consumer<Object, Object> consumer) {
        log.info("消息消费成功：{}", record.value());
    }

    @Override
    public void failure(ConsumerRecord<Object, Object> record, Exception exception, Consumer<Object, Object> consumer) {
        log.info("消息消费失败：{}", record.value());
    }

    @Override
    public void afterRecord(ConsumerRecord<Object, Object> record, Consumer<Object, Object> consumer) {
        log.info("消息消费不管成功还是失败，最终都会调用：{}", record.value());
    }
}