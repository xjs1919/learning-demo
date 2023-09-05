package com.github.xjs.config;

import com.github.xjs.Constants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public TraceIdInterceptor traceIdInterceptor(){
        return new TraceIdInterceptor();
    }

    public static class TraceIdInterceptor implements RequestInterceptor {

        @Override
        public void apply(RequestTemplate template) {
            template.header(Constants.TRACE_ID, MDC.get(Constants.TRACE_ID));
        }
    }

}
