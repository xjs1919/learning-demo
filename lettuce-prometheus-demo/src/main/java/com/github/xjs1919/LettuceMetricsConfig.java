package com.github.xjs1919;

import io.lettuce.core.metrics.MicrometerCommandLatencyRecorder;
import io.lettuce.core.metrics.MicrometerOptions;
import io.lettuce.core.resource.ClientResources;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.autoconfigure.data.redis.ClientResourcesBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @author xujiashuai
 * @date 2022/5/25 13:42
 **/
@Configuration
public class LettuceMetricsConfig {

    private MeterRegistry registry;

    public LettuceMetricsConfig(MeterRegistry registry){
        this.registry = registry;
    }

    @Bean
    public ClientResourcesBuilderCustomizer clientResourcesBuilderCustomizer(){
        return new ClientResourcesBuilderCustomizer(){
            @Override
            public void customize(ClientResources.Builder clientResourcesBuilder) {
                clientResourcesBuilder.commandLatencyRecorder(new MicrometerCommandLatencyRecorder(registry,
                        MicrometerOptions.builder()
                                .enable()
                                .histogram(true)
                                .minLatency(Duration.ofNanos(1L))
                                .build()));
            }
        };
    }

}
