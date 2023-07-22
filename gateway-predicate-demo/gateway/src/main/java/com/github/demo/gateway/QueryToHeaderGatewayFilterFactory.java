package com.github.demo.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.style.ToStringCreator;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;

@Component
public class QueryToHeaderGatewayFilterFactory extends AbstractGatewayFilterFactory<QueryToHeaderGatewayFilterFactory.QueryToHeaderConfig> {
//    @Override
//    public GatewayFilter apply(final AbstractNameValueGatewayFilterFactory.NameValueConfig config) {
//        return new GatewayFilter() {
//            @Override
//            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//                ServerHttpRequest request = exchange.getRequest();
//                String authorization = request.getQueryParams().getFirst("authorization");
//                if(!StringUtils.hasLength(authorization)){
//                    return chain.filter(exchange);
//                }
//                request.mutate().header("userinfo", authorization);
//                return chain.filter(exchange);
//            }
//        };
//    }

    public QueryToHeaderGatewayFilterFactory() {
        super(QueryToHeaderConfig.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("queryName", "headerName");
    }

    @Override
    public GatewayFilter apply(final QueryToHeaderConfig config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest request = exchange.getRequest();
                String queryValue = request.getQueryParams().getFirst(config.getQueryName());
                if(!StringUtils.hasLength(queryValue)){
                    return chain.filter(exchange);
                }
                request.mutate().header(config.getHeaderName(), queryValue);
                return chain.filter(exchange);
            }
        };
    }

    @Validated
    public static class QueryToHeaderConfig {
        protected @NotEmpty String queryName;
        protected @NotEmpty String headerName;

        public QueryToHeaderConfig() {
        }

        public String getQueryName() {
            return this.queryName;
        }

        public QueryToHeaderConfig setQueryName(String queryName) {
            this.queryName = queryName;
            return this;
        }

        public String getHeaderName() {
            return this.headerName;
        }

        public QueryToHeaderConfig setHeaderName(String headerName) {
            this.headerName = headerName;
            return this;
        }

        @Override
        public String toString() {
            return (new ToStringCreator(this)).append("headerName", this.headerName).append("queryName", this.queryName).toString();
        }
    }

}
