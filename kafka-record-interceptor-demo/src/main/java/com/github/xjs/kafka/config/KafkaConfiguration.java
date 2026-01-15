package com.github.xjs.kafka.config;

import com.github.xjs.kafka.filter.TraceIdFilter;
import com.github.xjs.kafka.interceptor.ReceiveTraceIdRecordInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.CompositeRecordInterceptor;
import org.springframework.kafka.listener.RecordInterceptor;

@Slf4j
@Configuration
public class KafkaConfiguration  {

    @Bean
    public CompositeRecordInterceptor<Object, Object> compositeRecordInterceptor(){
        ReceiveTraceIdRecordInterceptor interceptor1 = new ReceiveTraceIdRecordInterceptor();
        RecordInterceptor<Object, Object> interceptor2 = (record, consumer) -> {
            log.info("interceptor2.intercept");
            return record;
        };
        return new CompositeRecordInterceptor<>(interceptor2, interceptor1);
    }

//    @Bean
//    public ReceiveTraceIdRecordInterceptor receiveTraceIdInterceptor(){
//        return new ReceiveTraceIdRecordInterceptor();
//    }

    @Bean
    public TraceIdFilter traceIdFilter(){
        return new TraceIdFilter();
    }

}
