package com.github.xjs.config;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerInterceptor;
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
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.transaction.KafkaTransactionManager;

import java.util.Map;
import java.util.Optional;


@Configuration
public class KafkaConfig {

    @Bean
    public KafkaAdmin.NewTopics createTopics(){
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("topic1").build(),
                TopicBuilder.name("topic2").build(),
                TopicBuilder.name("employee").build(),
                TopicBuilder.name("employee1").build(),
                TopicBuilder.name("employee2").build()
        );
    }

//    @Bean
//    DefaultKafkaProducerFactory<?, ?> kafkaProducerFactory(ObjectProvider<DefaultKafkaProducerFactoryCustomizer> customizers,
//                                                           KafkaProperties kafkaProperties) {
//        Map<String, Object> properties = kafkaProperties.buildProducerProperties();
//        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
//        DefaultKafkaProducerFactory<?, ?> factory = new DefaultKafkaProducerFactory<>(properties);
//        String transactionIdPrefix = kafkaProperties.getProducer().getTransactionIdPrefix();
//        if (transactionIdPrefix != null) {
//            factory.setTransactionIdPrefix(transactionIdPrefix);
//        }
//        customizers.orderedStream().forEach((customizer) -> customizer.customize(factory));
//        return factory;
//    }
//
//    @Bean
//    public KafkaTemplate kafkaTemplate(ProducerFactory<Object, Object> kafkaProducerFactory,
//                                       ProducerListener<Object, Object> kafkaProducerListener,
//                                       ObjectProvider<RecordMessageConverter> messageConverter,
//                                       Optional<ProducerInterceptor> producerInterceptor,
//                                       KafkaProperties properties){
//        PropertyMapper map = PropertyMapper.get();
//        KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate<>(kafkaProducerFactory);
//        messageConverter.ifUnique(kafkaTemplate::setMessageConverter);
//        kafkaTemplate.setProducerInterceptor(producerInterceptor.orElse(null));
//        map.from(kafkaProducerListener).to(kafkaTemplate::setProducerListener);
//        map.from(properties.getTemplate().getDefaultTopic()).to(kafkaTemplate::setDefaultTopic);
//        map.from(properties.getTemplate().getTransactionIdPrefix()).to(kafkaTemplate::setTransactionIdPrefix);
//        map.from(properties.getTemplate().isObservationEnabled()).to(kafkaTemplate::setObservationEnabled);
//        return kafkaTemplate;
//    }
//
//    @Bean
//    KafkaTransactionManager<?, ?> kafkaTransactionManager(ProducerFactory<?, ?> producerFactory) {
//        return new KafkaTransactionManager<>(producerFactory);
//    }


}
