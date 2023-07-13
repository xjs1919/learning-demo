package com.github.demo.gateway;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义的断言工厂<br>
 * 1.名称必须是配置+RoutePredicateFactory<br>
 * 2.必须继承AbstractRoutePredicateFactory<配置类>
 */
@Component
public class HourRoutePredicateFactory extends AbstractRoutePredicateFactory<HourRoutePredicateFactory.Config> {

    public HourRoutePredicateFactory() {
        super(HourRoutePredicateFactory.Config.class);
    }

    /**读取配置文件的参数值，赋值到配置类中的属性上*/
    @Override
    public List<String> shortcutFieldOrder() {
        //顺序必须与yml文件中的配置顺序对应
        return Arrays.asList("startHour", "endHour");
    }

    @Override
    public Predicate<ServerWebExchange> apply(HourRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //获取当前时间
                LocalDateTime now = LocalDateTime.now();
                int hour = now.getHour();
                if(hour >= config.getStartHour() && hour <= config.getEndHour()){
                    return true;
                }
                return false;
            }
        };
    }

    /**用于接收参数*/
    @Data
    @NoArgsConstructor
    public static class Config {
        private int startHour;
        private int endHour;
    }
}
