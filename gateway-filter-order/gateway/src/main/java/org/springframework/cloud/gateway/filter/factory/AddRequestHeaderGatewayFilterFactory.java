//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.springframework.cloud.gateway.filter.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.support.GatewayToStringStyler;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
public class AddRequestHeaderGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    public AddRequestHeaderGatewayFilterFactory() {
    }

    public GatewayFilter apply(final AbstractNameValueGatewayFilterFactory.NameValueConfig config) {
        return new GatewayFilter() {
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String value = ServerWebExchangeUtils.expand(exchange, config.getValue());
                ServerHttpRequest request = exchange.getRequest().mutate().headers((httpHeaders) -> {
                    httpHeaders.add(config.getName(), value);
                }).build();
                log.info("config name: "+config.getName()+", config value : " + value);
                return chain.filter(exchange.mutate().request(request).build());
            }

            public String toString() {
                return GatewayToStringStyler.filterToStringCreator(AddRequestHeaderGatewayFilterFactory.this).append(config.getName(), config.getValue()).toString();
            }
        };
    }
}
