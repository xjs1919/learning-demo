package com.github.xjs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 启动类
 * */
@Slf4j
@SpringBootApplication
public class H2Application implements ApplicationRunner {

    /**
     * main方法
     * @param args 参数
     * */
    public static void main(String[] args) {
        SpringApplication.run(H2Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        jdbcTemplate.query("select * from tb_user", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                log.info("id:{}, name:{}", id, name);
            }
        });
    }
}
