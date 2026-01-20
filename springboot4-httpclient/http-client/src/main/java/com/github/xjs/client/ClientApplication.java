package com.github.xjs.client;

import com.github.xjs.client.api.GoodsApi;
import com.github.xjs.client.api.OrderApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.service.registry.ImportHttpServices;
/**
 * https://docs.spring.io/spring-boot/reference/io/rest-client.html#io.rest-client.httpservice
 * */
@SpringBootApplication
//@ImportHttpServices(types = GoodsApi.class, group = "goods")
//@ImportHttpServices(types = OrderApi.class, group = "order")
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }
}
