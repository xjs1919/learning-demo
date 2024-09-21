package com.github.xjs.controller.webflux;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class RouterFunctionConfiguration {

    @Bean
    public RouterFunction routerFunction(){
        return RouterFunctions.route().GET("/webflux/routerFunction",
                request-> ServerResponse.ok()
                        .contentType(MediaType.TEXT_HTML)
                        .body(Mono.just("Webflux using RouterFunction"), String.class)
        ).build();
    }
}
