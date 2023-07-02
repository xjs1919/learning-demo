package com.github.xjs.dameng.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    @Autowired
    private DataSourceProperties dataSourceProperties;
    @Bean
    public HikariDataSource hikariDataSource(){
        com.github.xjs.dameng.config.HikariDataSource ds = new com.github.xjs.dameng.config.HikariDataSource();
        ds.setDriverClassName(dataSourceProperties.getDriverClassName());
        ds.setJdbcUrl(dataSourceProperties.getUrl());
        ds.setUsername(dataSourceProperties.getUsername());
        ds.setPassword(dataSourceProperties.getPassword());
        return ds;
    }
}
