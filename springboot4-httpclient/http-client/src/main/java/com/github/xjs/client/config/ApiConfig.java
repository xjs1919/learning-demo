package com.github.xjs.client.config;
import com.github.xjs.client.api.GoodsApi;
import com.github.xjs.client.api.OrderApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

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
                            .build());
        };
    }
}