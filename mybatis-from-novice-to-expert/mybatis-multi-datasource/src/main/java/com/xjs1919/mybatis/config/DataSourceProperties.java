package com.xjs1919.mybatis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiashuai.xujs
 * @date 2022/3/31 10:24
 */
@Data
@ConfigurationProperties(prefix = "app")
public class DataSourceProperties {

    private DSProperties ds1;

    private DSProperties ds2;

    @Data
    public static class DSProperties {
        private String url;
        private String username;
        private String password;
    }

}
