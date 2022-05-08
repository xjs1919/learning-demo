package com.xjs1919.mybatis.shard;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jiashuai.xujs
 * @date 2022/3/31 10:24
 */
@Data
@ConfigurationProperties(prefix = "app")
public class DataSourceProperties {

    private DSProperties shard0;

    private DSProperties shard1;

    @Data
    public static class DSProperties {
        private String url;
        private String username;
        private String password;
    }

}
