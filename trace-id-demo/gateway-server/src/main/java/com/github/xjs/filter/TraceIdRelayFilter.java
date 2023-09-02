package com.github.xjs.filter;

import com.github.xjs.Constants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Component
public class TraceIdRelayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.生成 traceId
        String traceId = UUID.randomUUID().toString();
        // 2.保存到日志变量池
        MDC.put(Constants.TRACE_ID, traceId);
        // 3.添加traceId到请求头，继续向微服务传递
        exchange.getRequest().mutate().header(Constants.TRACE_ID, traceId);
        exchange.getResponse().getHeaders().set(Constants.TRACE_ID, traceId);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
