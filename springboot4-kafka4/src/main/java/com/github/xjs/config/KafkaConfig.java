package com.github.xjs.config;

import com.github.xjs.pojo.Farewell;
import com.github.xjs.pojo.Greeting;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJacksonJsonMessageConverter;
import org.springframework.kafka.support.mapping.DefaultJacksonJavaTypeMapper;
import org.springframework.kafka.support.mapping.JacksonJavaTypeMapper;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    ProducerFactory<String, String> stringProducerFactory(KafkaProperties kafkaProperties){
        Map<String, Object> properties = kafkaProperties.buildProducerProperties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        DefaultKafkaProducerFactory<String, String> factory = new DefaultKafkaProducerFactory<>(properties);
        String transactionIdPrefix = kafkaProperties.getProducer().getTransactionIdPrefix();
        if (transactionIdPrefix != null) {
            factory.setTransactionIdPrefix(transactionIdPrefix);
        }
        return factory;
    }

    @Bean
    KafkaTemplate<String, String> stringKafkaTemplate(ProducerFactory stringProducerFactory){
        return new KafkaTemplate<>(stringProducerFactory);
    }

    @Bean
    DefaultKafkaConsumerFactory<String, String> stringKafkaConsumerFactory(
            KafkaProperties kafkaProperties) {
        Map<String, Object> properties = kafkaProperties.buildConsumerProperties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        DefaultKafkaConsumerFactory<String, String> factory = new DefaultKafkaConsumerFactory<>(properties);
        return factory;
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> stringKafkaListenerContainerFactory(ConsumerFactory stringKafkaConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stringKafkaConsumerFactory);
        return factory;
    }

    @Bean
    public NewTopic pojoTopic() {
        return new NewTopic("pojo-topic", 1, (short) 1);
    }

    @Bean
    ProducerFactory<String, Object> objectProducerFactory(KafkaProperties kafkaProperties){
        Map<String, Object> properties = kafkaProperties.buildProducerProperties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JacksonJsonSerializer.class);
        properties.put(JacksonJsonSerializer.TYPE_MAPPINGS,
                "greeting:com.github.xjs.pojo.Greeting, farewell:com.github.xjs.pojo.Farewell");
        DefaultKafkaProducerFactory<String, Object> factory = new DefaultKafkaProducerFactory<>(properties);
        return factory;
    }

    @Bean
    KafkaTemplate<String, Object> objectKafkaTemplate(ProducerFactory objectProducerFactory){
        return new KafkaTemplate<>(objectProducerFactory);
    }

    @Bean
    DefaultKafkaConsumerFactory<String, Object> objectKafkaConsumerFactory(
            KafkaProperties kafkaProperties) {
        Map<String, Object> properties = kafkaProperties.buildConsumerProperties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JacksonJsonDeserializer.class);
        properties.put(JacksonJsonDeserializer.TRUSTED_PACKAGES, "com.github.xjs.pojo");
        DefaultKafkaConsumerFactory<String, Object> factory = new DefaultKafkaConsumerFactory<>(properties);
        return factory;
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> objectKafkaListenerContainerFactory(ConsumerFactory objectKafkaConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(objectKafkaConsumerFactory);
        factory.setRecordMessageConverter(multiTypeConverter());
        return factory;
    }
    @Bean
    public RecordMessageConverter multiTypeConverter() {
        // 创建TypeMapper
        DefaultJacksonJavaTypeMapper typeMapper = new DefaultJacksonJavaTypeMapper();
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("greeting", Greeting.class);
        mappings.put("farewell", Farewell.class);
        typeMapper.setIdClassMapping(mappings);
        typeMapper.setTypePrecedence(JacksonJavaTypeMapper.TypePrecedence.TYPE_ID);
        typeMapper.addTrustedPackages("com.github.xjs.pojo");
        // 创建MessageConverter
        StringJacksonJsonMessageConverter converter = new StringJacksonJsonMessageConverter();
        converter.setTypeMapper(typeMapper);
        return converter;
    }
}
