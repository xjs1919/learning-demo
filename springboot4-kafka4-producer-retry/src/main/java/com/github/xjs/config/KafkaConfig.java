package com.github.xjs.config;
import com.github.xjs.controller.QuickProducer;
import com.github.xjs.util.ExceptionUtils;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.boot.kafka.autoconfigure.DefaultKafkaProducerFactoryCustomizer;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.CompositeProducerListener;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import tools.jackson.databind.util.ExceptionUtil;

import java.util.Map;
import java.util.Optional;


@Configuration
public class KafkaConfig {

    private static Logger log = LoggerFactory.getLogger(KafkaConfig.class);

    @Bean
    public KafkaAdmin.NewTopics createTopics(){
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("retry-topic")
                        .partitions(1)
                        .replicas(3)
                        .config("min.insync.replicas", "2")
                        .build()
        );
    }

    @Bean
    public ProducerListener producerListener(KafkaProperties kafkaProperties){
        return new ProducerListener() {
            @Override
            public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
                log.info("消息发送成功：{}", producerRecord.value());
            }

            @Override
            public void onError(ProducerRecord producerRecord, @Nullable RecordMetadata recordMetadata, Exception exception) {
                log.info("消息发送失败：{}， 异常详情:{}", producerRecord.value(), ExceptionUtils.getStackTraces(exception));
            }
        };
    }


}
