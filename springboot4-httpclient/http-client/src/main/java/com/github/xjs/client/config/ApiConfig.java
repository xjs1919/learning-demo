package com.github.xjs.client.config;
import com.github.xjs.client.api.GoodsApi;
import com.github.xjs.client.api.OrderApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.http.client.HttpClientSettings;
import org.springframework.boot.http.client.HttpRedirects;
import org.springframework.boot.restclient.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Slf4j
@Configuration
@ImportHttpServices(types = GoodsApi.class, group = "goods")
@ImportHttpServices(types = OrderApi.class, group = "order")
public class ApiConfig {
    @Bean
    RestClientHttpServiceGroupConfigurer groupConfigurer() {
        return groups -> {
            groups.filterByName("order")
                    .forEachClient((group, builder) -> builder
                            .baseUrl("http://localhost:8081")
                            .build());
            groups.filterByName("goods")
                    .forEachClient((group, builder) -> builder
                            .baseUrl("http://localhost:8082")
                            .defaultHeader("token", "123456")
                            .requestInterceptor(new ClientHttpRequestInterceptor() {
                                @Override
                                public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                                    request.getHeaders().add("traceId", "abcdefg");
                                    logRequest(request, body);
                                    return execution.execute(request, body);
                                }
                            })
                            .build());
        };
    }

    @Bean
    public HttpClientSettings httpClientSettings(){
        return new HttpClientSettings(HttpRedirects.FOLLOW, Duration.ofSeconds(1), Duration.ofSeconds(1), null);
    }

    @Bean
    public RestClientCustomizer  restClientCustomizer(){
        return restClientBuilder -> restClientBuilder.requestInterceptor(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                request.getHeaders().add("traceId", "abcdefg");
                logRequest(request, body);
                return execution.execute(request, body);
            }
        });
    }

    private void logRequest(HttpRequest request, byte[] body) {
            StringBuilder logBuilder = new StringBuilder();
            logBuilder.append("\n=== HTTP Request ===\n");
            logBuilder.append("URI     : ").append(request.getURI()).append("\n");
            logBuilder.append("Method  : ").append(request.getMethod()).append("\n");
            logBuilder.append("Headers : ").append(request.getHeaders()).append("\n");
            if (body.length > 0) {
                String bodyStr = new String(body, StandardCharsets.UTF_8);
                logBuilder.append("Body    : ").append(bodyStr).append("\n");
            }
            log.info(logBuilder.toString());
    }

}