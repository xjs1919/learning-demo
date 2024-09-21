package com.github.xjs.controller.servlet;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class RouterFunctionConfiguration {
    /**
     * 方式4：@Bean + RouterFunction
     * */
    @Bean
    public RouterFunction routerFunction(){
        return RouterFunctions.route().GET("/routerFunction",
                request-> ServerResponse.ok().contentType(MediaType.TEXT_HTML).body("Controller using RouterFunction")
        ).build();
    }
}
