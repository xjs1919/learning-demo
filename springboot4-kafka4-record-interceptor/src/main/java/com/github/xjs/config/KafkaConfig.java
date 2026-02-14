package com.github.xjs.config;

import com.github.xjs.kafka.filter.ReceiveTraceIdFilter;
import com.github.xjs.kafka.interceptor.ReceiveTraceIdConsumerInterceptor;
import com.github.xjs.kafka.interceptor.ReceiveTraceIdRecordInterceptor;
import com.github.xjs.kafka.interceptor.SendTraceIdInterceptor;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.boot.kafka.autoconfigure.DefaultKafkaConsumerFactoryCustomizer;
import org.springframework.boot.kafka.autoconfigure.DefaultKafkaProducerFactoryCustomizer;
import org.springframework.boot.kafka.autoconfigure.KafkaConnectionDetails;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.CompositeRecordInterceptor;
import org.springframework.kafka.listener.RecordInterceptor;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.converter.RecordMessageConverter;

import java.util.Map;
import java.util.Optional;

@Configuration
public class KafkaConfig {

    private static Logger log = LoggerFactory.getLogger(KafkaConfig.class);

    // ProducerInterceptor
    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            var kafkaTemplate = event.getApplicationContext().getBean(KafkaTemplate.class);
            kafkaTemplate.setProducerInterceptor(new SendTraceIdInterceptor());
        }
    }

    //RecordInterceptor
    @Bean
    public RecordInterceptor receiveTraceIdRecordInterceptor(){
        return new ReceiveTraceIdRecordInterceptor();
    }

    //RecordFilterStrategy
    @Bean
    public RecordFilterStrategy receiveTraceIdFilter(){
        return new ReceiveTraceIdFilter();
    }

    //ConsumerInterceptor
    @Bean
    DefaultKafkaConsumerFactory<?, ?> kafkaConsumerFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> properties = kafkaProperties.buildConsumerProperties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        properties.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG,
                "com.github.xjs.kafka.interceptor.ReceiveTraceIdConsumerInterceptor");
        DefaultKafkaConsumerFactory<Object, Object> factory = new DefaultKafkaConsumerFactory<>(properties);
        return factory;
    }

    @Bean
    public RecordInterceptor<Object, Object> compositeRecordInterceptor(){
        RecordInterceptor interceptor1 = new ReceiveTraceIdRecordInterceptor();
        RecordInterceptor interceptor2 = (record, consumer) -> {
            log.info("interceptor2.intercept");
            return record;
        };
        return new CompositeRecordInterceptor<>(interceptor2, interceptor1);
    }
}
