package com.github.xjs.config;

import com.github.xjs.kafka.interceptor.SendTraceIdInterceptor;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.converter.RecordMessageConverter;

import java.util.Optional;

// @Configuration
public class KafkaConfig {

    @Bean
    public ProducerInterceptor producerInterceptor(){
        return new SendTraceIdInterceptor();
    }

    @Bean
    public KafkaTemplate kafkaTemplate(ProducerFactory<Object, Object> kafkaProducerFactory,
                                       ProducerListener<Object, Object> kafkaProducerListener,
                                       ObjectProvider<RecordMessageConverter> messageConverter,
                                       Optional<ProducerInterceptor> producerInterceptor,
                                       KafkaProperties properties){
        PropertyMapper map = PropertyMapper.get();
        KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate<>(kafkaProducerFactory);
        messageConverter.ifUnique(kafkaTemplate::setMessageConverter);
        kafkaTemplate.setProducerInterceptor(producerInterceptor.orElse(null));
        map.from(kafkaProducerListener).to(kafkaTemplate::setProducerListener);
        map.from(properties.getTemplate().getDefaultTopic()).to(kafkaTemplate::setDefaultTopic);
        map.from(properties.getTemplate().getTransactionIdPrefix()).to(kafkaTemplate::setTransactionIdPrefix);
        map.from(properties.getTemplate().isObservationEnabled()).to(kafkaTemplate::setObservationEnabled);
        return kafkaTemplate;
    }
}
