package com.github.xjs.kafka.config;

import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaRetryTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.retrytopic.RetryTopicConfiguration;
import org.springframework.kafka.retrytopic.RetryTopicConfigurationBuilder;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.backoff.ExponentialBackOff;


@Configuration
@EnableKafkaRetryTopic //non-blocking:1
public class KafkaConfiguration {

//    @Bean
//    public DefaultErrorHandler errorHandler(KafkaTemplate kafkaTemplate){
//        ExponentialBackOff backOff = new ExponentialBackOff();
//        backOff.setInitialInterval(3000);
//        backOff.setMultiplier(3);
//        backOff.setMaxAttempts(2);
//        return new DefaultErrorHandler(null,backOff);
//    }

//    @Bean
//    public DefaultErrorHandler errorHandler(KafkaTemplate kafkaTemplate){
//        var recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate,
//                (cr, e)-> new TopicPartition(cr.topic()+".DLT", cr.partition()));
//        ExponentialBackOff backOff = new ExponentialBackOff();
//        backOff.setMaxInterval(Long.MAX_VALUE);
//        backOff.setInitialInterval(3000);
//        backOff.setMultiplier(3);
//        backOff.setMaxAttempts(2);
//        return new DefaultErrorHandler(recoverer,backOff);
//    }

    // non-blocking:2
    @Bean
    TaskScheduler scheduler() {
        return new ThreadPoolTaskScheduler();
    }

    // non-blocking:3
    @Bean
    public RetryTopicConfiguration myRetryConfiguration(KafkaTemplate<String, String> template) {
        return RetryTopicConfigurationBuilder
                .newInstance()
                //.fixedBackOff(3000)
                .exponentialBackoff(3000, 10, Long.MAX_VALUE)
                .maxAttempts(3)
                .dltSuffix(".DLT")
                .create(template);
    }

}
